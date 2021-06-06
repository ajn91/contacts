package jafari.alireza.contacts.model.repository.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.contacts.R
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.list.ListModel
import jafari.alireza.contacts.model.performGetOperation
import jafari.alireza.contacts.model.source.external.list.pojo.ContactExternal
import jafari.alireza.contacts.model.source.external.list.pojo.asDatabaseEntities
import jafari.alireza.contacts.model.source.external.list.pojo.asListModel
import jafari.alireza.contacts.model.source.local.list.datasource.ListExternalDataSource
import jafari.alireza.contacts.model.source.local.list.datasource.ListLocalDataSource
import jafari.alireza.contacts.model.source.local.list.entity.asListModels

import javax.inject.Inject

class ListRepositoryImp @Inject constructor(
    private val externalDataSource: ListExternalDataSource,
    private val localDataSource: ListLocalDataSource,
    @ApplicationContext val context: Context
) : ListRepository {
    override fun getContacts(): LiveData<Resource<List<ListModel>?>> {
        return performGetOperation<List<ListModel>, List<ContactExternal>>(
//          context.getString(R.string.error_no_item_available)
"" ,
            localFetch = {
                localDataSource.getContacts().map {
                    if (it.isNotEmpty()) it.asListModels() else null

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


}