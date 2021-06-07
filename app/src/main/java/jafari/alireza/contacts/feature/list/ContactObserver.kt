package jafari.alireza.contacts.feature.list

import android.database.ContentObserver
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ContactObserver(val status: MutableLiveData<Boolean>) : ContentObserver(null) {
    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        status.postValue( selfChange)
    }

    override fun deliverSelfNotifications(): Boolean {
        return true
    }
}