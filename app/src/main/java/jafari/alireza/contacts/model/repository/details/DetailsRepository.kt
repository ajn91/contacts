package jafari.alireza.contacts.model.repository.details

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.details.DetailsModel

interface DetailsRepository {
    fun getDetails(id: Long): LiveData<Resource<DetailsModel?>>



}