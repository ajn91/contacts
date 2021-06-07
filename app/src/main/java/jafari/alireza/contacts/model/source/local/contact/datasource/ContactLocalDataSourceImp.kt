package jafari.alireza.contacts.model.source.local.list.datasource

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity
import javax.inject.Inject

class ContactLocalDataSourceImp @Inject constructor(
    private val contactDao: ContactDao
) : ContactLocalDataSource {

    override fun getContacts(): List<ContactEntity> =
        contactDao.getAll()

    override suspend fun saveAll(contactList: List<ContactEntity>): List<Long> =
        contactDao.insertAll(contactList)

    override suspend fun clear() {
        contactDao.deleteAll()
    }


}