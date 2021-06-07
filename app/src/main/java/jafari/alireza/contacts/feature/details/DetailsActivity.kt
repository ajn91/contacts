package jafari.alireza.contacts.feature.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jafari.alireza.contacts.BR
import jafari.alireza.contacts.R
import jafari.alireza.contacts.databinding.DetailsActivityBinding
import jafari.alireza.contacts.feature.appinterface.OnItemClickListener
import jafari.alireza.contacts.feature.base.BaseActivity
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.details.DetailsModel
import jafari.alireza.contacts.utils.NavigationUtils
import javax.inject.Inject


@AndroidEntryPoint
class DetailsActivity : BaseActivity<DetailsActivityBinding, DetailsViewModel>(),
    OnItemClickListener<String> {


    override val mViewModel: DetailsViewModel by viewModels()


    @Inject
    lateinit var phoneAdapter: PhoneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseView()

    }

    private fun setUpList() {
        val spanCount = resources.getInteger(R.integer.search_list_span_count)
        val layoutManager = GridLayoutManager(this, spanCount)

        viewDataBinding?.rcv?.apply {
            this.layoutManager = layoutManager
            adapter = phoneAdapter
//            addItemDecoration(UiUtils.createDivider(this@ExploreActivity))

        }
        phoneAdapter.onItemClickListener = this

    }



    override fun getBindingVariable(): Pair<Int, Any?> {
        return Pair(BR.viewModel, mViewModel)
    }

    override fun getLayoutId(): Int {
        return R.layout.details_activity
    }

    override fun setupObserver() {
        mViewModel.detailsLive.observe(this, ::handleItem)
    }


    private fun handleItem(resource: Resource<DetailsModel?>) {
        when (resource.status) {
            Resource.Status.SUCCESS -> {
                viewDataBinding?.toolbar?.toolbar?.title = getString(R.string.app_name)
                setUpDetailsView(resource.data)
            }
            Resource.Status.LOADING -> {
                viewDataBinding?.toolbar?.toolbar?.title = getString(R.string.loading)

            }
            Resource.Status.ERROR -> {
             finish()
            }

        }
    }


    private fun setUpDetailsView(detailsModel: DetailsModel?) {
        detailsModel?.phoneNumber?.let {
            phoneAdapter.setItems(it)
        }
    }


    private fun initialiseView() {
        setUpActionBar()
        setUpList()
    }

    private fun setUpActionBar() {
        setSupportActionBar(viewDataBinding?.toolbar?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewDataBinding?.toolbar?.toolbar?.setNavigationIconColor(
            ResourcesCompat.getColor(resources, R.color.textTitleColor, null)
        )

    }

    fun Toolbar.setNavigationIconColor(@ColorInt color: Int) = navigationIcon?.mutate()?.let {
        it.setTint(color)
        this.navigationIcon = it
    }

    private fun openCallWithIntent(phone: CharSequence?) {
        phone?.let {
            NavigationUtils.openCallWithIntent(this, it.toString())
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onItemClick(item: String) {
        openCallWithIntent(item)

    }
}
