package jafari.alireza.contacts.model.source.local.list.datasource

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.source.local.contact.entity.ContactEntity
import javax.inject.Inject

class ListLocalDataSourceImp @Inject constructor(
    private val listDao: ListDao
) : ListLocalDataSource {
    override fun getContactsLive(): LiveData<List<ContactEntity>> =
        listDao.getAllLive()




}