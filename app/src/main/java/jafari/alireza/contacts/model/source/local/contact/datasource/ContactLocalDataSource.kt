package jafari.alireza.contacts.model.source.local.list.datasource

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity


interface ContactLocalDataSource {
    fun getContacts(): List<ContactEntity>
    suspend fun saveAll(contactList: List<ContactEntity>): List<Long>
    suspend fun save(contact: ContactEntity): Long
    suspend fun delete(contacts: List<ContactEntity>)
    suspend fun clear()
}