package jafari.alireza.contacts.feature.details

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jafari.alireza.contacts.BR
import jafari.alireza.contacts.R
import jafari.alireza.contacts.databinding.ListActivityBinding
import jafari.alireza.contacts.feature.base.BaseActivity
import jafari.alireza.contacts.feature.list.ContactObserver
import jafari.alireza.contacts.feature.list.ListAdapter
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.list.ListModel
import jafari.alireza.contacts.utils.*
import jafari.alireza.foursquare.ui.appinterface.OnItemClickListener
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class ListActivity : BaseActivity<ListActivityBinding, ListViewModel>(),
    OnItemClickListener<ListModel> {

    override val mViewModel: ListViewModel by viewModels()


    @Inject
    lateinit var listAdapter: ListAdapter


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
        mViewModel.itemLoadedCount.observe(this, ::handleNeedToRenewData)
        mViewModel.needInitializeLive.observe(this, {})

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseView()
        checkPermissionAndShow()

    }

    override fun onResume() {
        super.onResume()
//        mViewModel.updateContacts()
    }


    private fun handleNeedToRenewData(itemLoadedCount: Int?) {
//        if (itemLoadedCount == 0)
//            ListAdapter.removeAllItems()
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
            MessageUtils.showInfoDialog(
                supportFragmentManager,
                "",
                statusNormal
            )
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
            mViewModel.getItems()
            observeContact()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CODE_READ_CONTACTS
            )
        }
    }




    override fun onItemClick(item: ListModel) {
        mViewModel.onItemClick(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onDestroy() {
        mViewModel.onStop()
        super.onDestroy()
    }


    companion object {
        const val REQUEST_CODE_READ_CONTACTS = 855
        const val PERMISSION_DIALOG_TAG = "permissionDialogTag"
    }


}
