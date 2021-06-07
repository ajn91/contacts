package jafari.alireza.contacts.model.repository.contact

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.contacts.model.source.external.contact.pojo.asDatabaseEntities
import jafari.alireza.contacts.model.source.local.list.datasource.ContactExternalDataSource
import jafari.alireza.contacts.model.source.local.list.datasource.ContactLocalDataSource
import jafari.alireza.contacts.model.source.local.list.datasource.ListLocalDataSource

import javax.inject.Inject

class ContactRepositoryImp @Inject constructor(
    private val externalDataSource: ContactExternalDataSource,
    private val localDataSource: ContactLocalDataSource,
    @ApplicationContext val context: Context
) : ContactRepository {


    override suspend fun updateContacts() {
        val newData = externalDataSource.getContacts().asDatabaseEntities()
        localDataSource.saveAll(newData)
//        val oldData =  localDataSource.getContacts()
//        val newData = externalDataSource.getContacts().asDatabaseEntities()
//        val diffCallback = ContactsLocalDiffCallback(oldData, newData)
//        val diffResult = DiffUtil.calculateDiff(diffCallback, false)
//        diffResult.dispatchUpdatesTo(object :ListUpdateCallback{
//            override fun onInserted(position: Int, count: Int) {
//                localDataSource.save(newData.get(position))
//            }
//
//            override fun onRemoved(position: Int, count: Int) {
//            }
//
//            override fun onMoved(fromPosition: Int, toPosition: Int) {
//            }
//
//            override fun onChanged(position: Int, count: Int, payload: Any?) {
//            }
//        })

    }


}