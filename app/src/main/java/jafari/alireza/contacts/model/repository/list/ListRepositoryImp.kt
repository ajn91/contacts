package jafari.alireza.contacts.model.repository.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.contacts.R
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.list.ListModel
import jafari.alireza.contacts.model.performGetOperation
import jafari.alireza.contacts.model.source.external.contact.datasource.ContactExternalDataSource
import jafari.alireza.contacts.model.source.external.contact.pojo.asDatabaseEntities
import jafari.alireza.contacts.model.source.local.contact.datasource.ContactLocalDataSource
import jafari.alireza.contacts.model.source.local.contact.entity.asListModels
import jafari.alireza.contacts.model.source.local.list.datasource.ListLocalDataSource
import javax.inject.Inject

class ListRepositoryImp @Inject constructor(
    private val externalDataSource: ContactExternalDataSource,
    private val localDataSource: ListLocalDataSource,
    private val  contactLocalDataSource: ContactLocalDataSource,
    @ApplicationContext val context: Context
) : ListRepository {
    override fun getContacts(): LiveData<Resource<List<ListModel>?>> {
        return performGetOperation(
            context.getString(R.string.error_no_item_available),
            localFetch = {
                localDataSource.getContactsLive().map {
                    it.asListModels()

                }
            },
            externalFetch = ({
                externalDataSource.getContacts()
            }),
            saveExternalResult = {
                contactLocalDataSource.clear()
                contactLocalDataSource.saveAll(it.asDatabaseEntities())
            }
        )
    }



}