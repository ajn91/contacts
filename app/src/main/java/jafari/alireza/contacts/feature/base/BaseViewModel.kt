package jafari.alireza.contacts.feature.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jafari.alireza.contacts.model.repository.contact.ContactRepository
import jafari.alireza.contacts.utils.Event


open class BaseViewModel constructor( val contactRepository: ContactRepository ) : ViewModel() {

    protected val _messageStringLive = MutableLiveData<Event<String>>()
    val messageStringLive: LiveData<Event<String>>
        get() = _messageStringLive
    protected val _messageIdLive = MutableLiveData<Event<Int>>()
    val messageIdLive: LiveData<Event<Int>>
        get() = _messageIdLive


    open fun onStop() {
    }



}
