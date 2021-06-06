package jafari.alireza.contacts.model.source.local.list.datasource

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity
import javax.inject.Inject

class ListLocalDataSourceImp @Inject constructor(
    private val listDao: ListDao
) : ListLocalDataSource {
    override fun getContacts(): LiveData<List<ContactEntity>> =
        listDao.getAll()

    override suspend fun save(contact: ContactEntity): Long = listDao.insert(contact)


    override suspend fun saveAll(contactList: List<ContactEntity>): List<Long> =
        listDao.insertAll(contactList)

    override suspend fun clear() {
        listDao.deleteAll()
    }


}