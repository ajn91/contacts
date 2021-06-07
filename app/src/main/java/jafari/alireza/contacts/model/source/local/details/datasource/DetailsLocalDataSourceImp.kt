package jafari.alireza.contacts.model.source.local.details.datasource

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.source.local.contact.entity.ContactEntity
import javax.inject.Inject

class DetailsLocalDataSourceImp @Inject constructor(
    private val detailsDao: DetailsDao
) : DetailsLocalDataSource {
    override fun getDetails(id: Long): LiveData<ContactEntity?> = detailsDao.getDetails(id)



}