package jafari.alireza.contacts.feature.base


import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    var viewDataBinding: T? = null
        private set
    abstract val mViewModel: V?



    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Pair<Int, Any?>

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setupObserver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

    }



    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        setupObserver()
        viewDataBinding!!.setVariable(getBindingVariable().first, getBindingVariable().second)
        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.executePendingBindings()

    }


    override fun onDestroy() {
        mViewModel?.onStop()
        super.onDestroy()
    }


}




