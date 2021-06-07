package jafari.alireza.contacts.model.repository.details


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.contacts.R
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.details.DetailsModel
import jafari.alireza.contacts.model.performGetOperation
import jafari.alireza.contacts.model.source.local.contact.entity.ContactEntity
import jafari.alireza.contacts.model.source.local.contact.entity.asDetailsModel
import jafari.alireza.contacts.model.source.local.details.datasource.DetailsLocalDataSource
import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    private val localDataSource: DetailsLocalDataSource,
    @ApplicationContext val context: Context
) : DetailsRepository {

    override fun getDetails(id: Long): LiveData<Resource<DetailsModel?>> {

        return performGetOperation<DetailsModel, ContactEntity>(
            context.getString(R.string.error_no_item_available),
            localFetch = {
                localDataSource.getDetails(id).map { it?.asDetailsModel() }
            }
        )
    }


}