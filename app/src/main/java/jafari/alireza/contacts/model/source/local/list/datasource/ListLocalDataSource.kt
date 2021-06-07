package jafari.alireza.contacts.model.source.local.list.datasource

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.source.local.contact.entity.ContactEntity


interface ListLocalDataSource {
    fun getContactsLive(): LiveData<List<ContactEntity>>

}