package jafari.alireza.contacts.model.repository.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.list.ListModel
import jafari.alireza.contacts.model.performGetOperation
import jafari.alireza.contacts.model.source.external.contact.pojo.ContactExternal
import jafari.alireza.contacts.model.source.external.contact.pojo.asDatabaseEntities
import jafari.alireza.contacts.model.source.local.list.datasource.ContactExternalDataSource
import jafari.alireza.contacts.model.source.local.list.datasource.ListLocalDataSource
import jafari.alireza.contacts.model.source.local.list.entity.asListModels

import javax.inject.Inject

class ListRepositoryImp @Inject constructor(
    private val externalDataSource: ContactExternalDataSource,
    private val localDataSource: ListLocalDataSource,
    @ApplicationContext val context: Context
) : ListRepository {
    override fun getContacts(): LiveData<Resource<List<ListModel>?>> {
        return performGetOperation<List<ListModel>, List<ContactExternal>>(
//          context.getString(R.string.error_no_item_available)
            "",
            localFetch = {
                localDataSource.getContactsLive().map {
                    it.asListModels()

                }
            },
            externalFetch = ({
                externalDataSource.getContacts()
            }),
            saveExternalResult = {
                localDataSource.saveAll(it.asDatabaseEntities())
            }
        )
    }

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