package jafari.alireza.contacts.model.source.local.contact.datasource

import jafari.alireza.contacts.model.source.local.contact.entity.ContactEntity


interface ContactLocalDataSource {
    fun getContacts(): List<ContactEntity>
    suspend fun saveAll(contactList: List<ContactEntity>): List<Long>
    suspend fun save(contact: ContactEntity): Long
    fun delete(contacts: List<ContactEntity>)
    suspend fun clear()
}