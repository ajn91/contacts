package jafari.alireza.contacts.feature.list

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.*
import dagger.hilt.android.AndroidEntryPoint
import jafari.alireza.contacts.BR
import jafari.alireza.contacts.R
import jafari.alireza.contacts.databinding.ListActivityBinding
import jafari.alireza.contacts.feature.appinterface.OnItemClickListener
import jafari.alireza.contacts.feature.base.BaseActivity
import jafari.alireza.contacts.feature.details.DetailsActivity
import jafari.alireza.contacts.feature.service.ContactWorker
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.list.ListModel
import jafari.alireza.contacts.utils.DetailsParams
import jafari.alireza.contacts.utils.DirectionParamName
import jafari.alireza.contacts.utils.EventObserver
import jafari.alireza.contacts.utils.NavigationUtils
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class ListActivity : BaseActivity<ListActivityBinding, ListViewModel>(),
    OnItemClickListener<ListModel> {

    override val mViewModel: ListViewModel by viewModels()


    @Inject
    lateinit var listAdapter: ListAdapter


    var contactWorker: OneTimeWorkRequest? = null

    override fun getBindingVariable(): Pair<Int, Any?> {
        return Pair(BR.viewModel, mViewModel)
    }

    override fun getLayoutId(): Int {
        return R.layout.list_activity
    }

    override fun setupObserver() {
        mViewModel.directToPageLive.observe(this, EventObserver(::directToPage))
        mViewModel.messageStringLive.observe(this, EventObserver(::handleMessage))
        mViewModel.messageIdLive.observe(this, EventObserver(::handleMessageResource))
        mViewModel.itemsLive.observe(this, ::handleItems)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseView()
        checkPermissionAndShow()

    }


    private fun handleMessageResource(id: Int?) {
        id?.let {
            viewDataBinding?.txtListStatus?.text = getString(it)
        } ?: Timber.d("id is null")
    }

    private fun handleMessage(message: String?) {
        viewDataBinding?.txtListStatus?.text = message

    }

    private fun directToPage(directionParamName: DirectionParamName?) {
        when (directionParamName) {
            is DirectionParamName.DetailsParams -> goToDetailsPage(directionParamName.id)

        }
    }

    private fun goToDetailsPage(id: Long) {
        val bundle = Bundle()
        bundle.putLong(DetailsParams.ID_NAME, id)
        NavigationUtils.startActivity(this, DetailsActivity::class.java, bundle)
    }

    private fun handleItems(result: Resource<List<ListModel>?>?) {

        when (result?.status) {
            Resource.Status.SUCCESS ->
                result.data?.let {
                    if (it.isEmpty()) {
                        setStatusText(
                            getString(R.string.error_no_item_available)
                        )
                        return
                    }
                    listAdapter.setItems(it)
                    mViewModel.setItemLoadedCount(listAdapter.itemCount)
                }

            Resource.Status.LOADING ->
                setStatusText(getString(R.string.loading))

            Resource.Status.ERROR ->
                setStatusText(
                    getString(R.string.error_no_item_available)
                )


        }
    }

    private fun setStatusText(statusNoItem: String?, statusNormal: String? = null) {
        if (mViewModel.itemLoadedCount.value == 0)
            viewDataBinding?.txtListStatus?.text = statusNoItem
        else if (statusNormal != null)
            Toast.makeText(baseContext, statusNormal, Toast.LENGTH_LONG).show()
    }


    private fun initialiseView() {
        setSupportActionBar(viewDataBinding?.toolbar?.toolbar!!)
        setUpList()
    }

    private fun setUpList() {
        val spanCount = resources.getInteger(R.integer.search_list_span_count)
        val layoutManager = GridLayoutManager(this, spanCount)

        viewDataBinding?.rcv?.apply {
            this.layoutManager = layoutManager
            adapter = listAdapter
//            addItemDecoration(UiUtils.createDivider(this@ExploreActivity))

        }
        listAdapter.onItemClickListener = this

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_READ_CONTACTS) {
            checkPermissionAndShow()
        }
    }

    private fun checkPermissionAndShow() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startContactService()
            mViewModel.getItems()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CODE_READ_CONTACTS
            )
        }
    }

    private fun startContactService() {
        if (contactWorker != null)
            return
        val constraint =
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED).build()

        contactWorker =
            OneTimeWorkRequest.Builder(ContactWorker::class.java)
                .setConstraints(constraint)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()

        WorkManager.getInstance(this).enqueue(contactWorker!!)


    }


    override fun onItemClick(item: ListModel) {
        mViewModel.onItemClick(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
        mViewModel.onStop()
        if (contactWorker != null) {
            WorkManager.getInstance(this).cancelWorkById(contactWorker!!.id)
            contactWorker = null
        }

    }


    companion object {
        const val REQUEST_CODE_READ_CONTACTS = 855
    }


}
