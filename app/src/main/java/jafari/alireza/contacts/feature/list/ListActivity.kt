package jafari.alireza.contacts.feature.details

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jafari.alireza.contacts.R
import jafari.alireza.contacts.databinding.ListActivityBinding
import jafari.alireza.contacts.feature.base.BaseActivity


@AndroidEntryPoint
class ListActivity : BaseActivity<ListActivityBinding, ListViewModel>(){

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
        return R.layout.list_activity
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
    }







}
