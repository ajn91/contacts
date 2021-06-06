package jafari.alireza.contacts.model.source.local.list.datasource

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity


interface ListLocalDataSource {
    fun getContacts(): LiveData<List<ContactEntity>>
    suspend fun save(contact: ContactEntity): Long
    suspend fun saveAll(contactList: List<ContactEntity>): List<Long>
    suspend fun clear()
}