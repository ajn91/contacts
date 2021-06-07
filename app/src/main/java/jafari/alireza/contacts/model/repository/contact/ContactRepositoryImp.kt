package jafari.alireza.contacts.model.repository.contact

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.contacts.model.source.external.contact.pojo.asDatabaseEntities
import jafari.alireza.contacts.model.source.local.list.datasource.ContactExternalDataSource
import jafari.alireza.contacts.model.source.local.list.datasource.ContactLocalDataSource
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity
import javax.inject.Inject


class ContactRepositoryImp @Inject constructor(
    private val externalDataSource: ContactExternalDataSource,
    private val localDataSource: ContactLocalDataSource,
    @ApplicationContext val context: Context
) : ContactRepository {


    override suspend fun updateContacts() {
        val oldData = localDataSource.getContacts()
        val newData = externalDataSource.getContacts().asDatabaseEntities()
        removeData(oldData, newData)
        UpdateData(oldData,newData)

    }

    private suspend fun removeData(oldData: List<ContactEntity>, newData: List<ContactEntity>) {
        val needToRemoveList = arrayListOf<ContactEntity>()
        needToRemoveList.addAll(oldData)
        needToRemoveList.removeAll(newData)
        if (needToRemoveList.isNotEmpty())
            localDataSource.delete(needToRemoveList)

    }

    private suspend fun UpdateData(oldData: List<ContactEntity>, newData: List<ContactEntity>) {
        val needToUpdateList = arrayListOf<ContactEntity>()
        needToUpdateList.addAll(newData)
        needToUpdateList.removeAll(oldData)
        if (needToUpdateList.isNotEmpty())
            localDataSource.saveAll(needToUpdateList)

    }


}