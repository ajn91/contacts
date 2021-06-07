package jafari.alireza.contacts.model.repository.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.details.DetailsModel
import jafari.alireza.contacts.model.performGetOperation
import jafari.alireza.contacts.model.source.local.details.datasource.DetailsLocalDataSource
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity
import jafari.alireza.contacts.model.source.local.list.entity.asDetailsModel


import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    private val localDataSource: DetailsLocalDataSource,
    @ApplicationContext val context: Context
) : DetailsRepository {

    override fun getDetails(id: Long): LiveData<Resource<DetailsModel?>> {

        return performGetOperation<DetailsModel, ContactEntity>(
            "",
            localFetch = {
                localDataSource.getDetails(id).map {  it?.asDetailsModel()}
            }
        )
    }


}