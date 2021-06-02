package jafari.alireza.contacts.feature.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jafari.alireza.contacts.utils.Event


open class BaseViewModel : ViewModel() {

    protected val _messageStringLive = MutableLiveData<Event<String>>()
    val messageStringLive: LiveData<Event<String>>
        get() = _messageStringLive
    protected val _messageIdLive = MutableLiveData<Event<Int>>()
    val messageIdLive: LiveData<Event<Int>>
        get() = _messageIdLive
    var isNetworkAvailable: Boolean = false


    open fun onStop() {
    }


    fun onNetworkAvailable() {
        isNetworkAvailable = true
    }

    fun onNetworkLost() {
        isNetworkAvailable = false
    }
}
