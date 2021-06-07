package jafari.alireza.contacts.feature.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jafari.alireza.contacts.model.repository.contact.ContactRepository
import jafari.alireza.contacts.model.repository.list.ListRepository
import jafari.alireza.contacts.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


open class BaseViewModel constructor( val contactRepository: ContactRepository, ) : ViewModel() {

    protected val _messageStringLive = MutableLiveData<Event<String>>()
    val messageStringLive: LiveData<Event<String>>
        get() = _messageStringLive
    protected val _messageIdLive = MutableLiveData<Event<Int>>()
    val messageIdLive: LiveData<Event<Int>>
        get() = _messageIdLive


    open fun onStop() {
    }



}
