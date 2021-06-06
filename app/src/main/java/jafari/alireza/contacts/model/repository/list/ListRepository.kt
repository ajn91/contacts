package jafari.alireza.contacts.model.repository.list

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.list.ListModel

interface ListRepository {

    fun getContacts(

    ): LiveData<Resource<List<ListModel>?>>
}