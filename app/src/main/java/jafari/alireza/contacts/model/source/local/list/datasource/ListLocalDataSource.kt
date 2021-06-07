package jafari.alireza.contacts.model.source.local.list.datasource

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity


interface ListLocalDataSource {
    fun getContactsLive(): LiveData<List<ContactEntity>>
    suspend fun saveAll(contactList: List<ContactEntity>): List<Long>
    suspend fun clear()
}