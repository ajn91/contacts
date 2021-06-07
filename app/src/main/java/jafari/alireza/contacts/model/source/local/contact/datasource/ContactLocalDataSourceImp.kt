package jafari.alireza.contacts.model.source.local.contact.datasource

import jafari.alireza.contacts.model.source.local.contact.entity.ContactEntity
import javax.inject.Inject

class ContactLocalDataSourceImp @Inject constructor(
    private val contactDao: ContactDao
) : ContactLocalDataSource {

    override fun getContacts(): List<ContactEntity> =
        contactDao.getAll()

    override suspend fun saveAll(contactList: List<ContactEntity>): List<Long> =
        contactDao.insertAll(contactList)

    override suspend fun save(contact: ContactEntity): Long = contactDao.insert(contact)
    override fun delete(contacts: List<ContactEntity>) {
        contactDao.delete(contacts)
    }

    override suspend fun clear() {
        contactDao.deleteAll()
    }


}