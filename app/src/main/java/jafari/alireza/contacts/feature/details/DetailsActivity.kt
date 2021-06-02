package jafari.alireza.contacts.feature.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import dagger.hilt.android.AndroidEntryPoint
import jafari.alireza.contacts.R
import jafari.alireza.contacts.databinding.DetailsActivityBinding
import jafari.alireza.contacts.feature.base.BaseActivity


@AndroidEntryPoint
class DetailsActivity : BaseActivity<DetailsActivityBinding, ListViewModel>(){

    override val mViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseView()

    }

    override fun onStart() {
        super.onStart()

    }

    override fun getBindingVariable(): Pair<Int, Any?> {
        return Pair(3,4)
//        return Pair(BR.viewModel, mViewModel)
    }

    override fun getLayoutId(): Int {
        return R.layout.details_activity
    }

    override fun setupObserver() {
    }




    override fun onStop() {

        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()
    }




    private fun initialiseView() {
//        setSupportActionBar(viewDataBinding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }









    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun Toolbar.setNavigationIconColor(@ColorInt color: Int) = navigationIcon?.mutate()?.let {
        it.setTint(color)
        this.navigationIcon = it
    }

    companion object {
        const val ERROR_DIALOG_TAG = "exit"
    }
}
