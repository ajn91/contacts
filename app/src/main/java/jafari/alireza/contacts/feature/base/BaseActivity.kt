package jafari.alireza.contacts.feature.base


import android.os.Bundle
import android.provider.ContactsContract
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import jafari.alireza.contacts.feature.list.ContactObserver


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    var viewDataBinding: T? = null
        private set
    abstract val mViewModel: V?

    var contactObserver: ContactObserver? = null


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
    protected fun observeContact() {
        contactObserver = ContactObserver(mViewModel!!.contactChangeLive)
       contentResolver
            .registerContentObserver(
                ContactsContract.Contacts.CONTENT_URI, true, contactObserver!!
            )

    }

    override fun onDestroy() {
        mViewModel?.onStop()
        if (contactObserver != null)
        contentResolver.unregisterContentObserver(contactObserver!!)
        super.onDestroy()
    }


}




