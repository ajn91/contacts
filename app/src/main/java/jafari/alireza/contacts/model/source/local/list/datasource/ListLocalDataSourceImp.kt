package jafari.alireza.contacts.model.source.local.list.datasource

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity
import javax.inject.Inject

class ListLocalDataSourceImp @Inject constructor(
    private val listDao: ListDao
) : ListLocalDataSource {
    override fun getContactsLive(): LiveData<List<ContactEntity>> =
        listDao.getAllLive()



    override suspend fun saveAll(contactList: List<ContactEntity>): List<Long> =
        listDao.insertAll(contactList)

    override suspend fun clear() {
        listDao.deleteAll()
    }


}