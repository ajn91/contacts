package jafari.alireza.contacts.feature.base

import android.database.ContentObserver
import android.util.EventLog
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import jafari.alireza.contacts.utils.Event

class ContactObserver(val status: MutableLiveData<Event<Boolean>>) : ContentObserver(null) {
    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        status.postValue( Event(true))
    }

    override fun deliverSelfNotifications(): Boolean {
        return false
    }
}