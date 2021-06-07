package jafari.alireza.contacts.model.source.local.details.datasource

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.source.local.contact.entity.ContactEntity


interface DetailsLocalDataSource {
    fun getDetails(id: Long): LiveData<ContactEntity?>


}