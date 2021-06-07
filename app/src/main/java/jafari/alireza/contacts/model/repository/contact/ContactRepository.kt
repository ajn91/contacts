package jafari.alireza.contacts.model.repository.contact

import androidx.lifecycle.LiveData
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.list.ListModel

interface ContactRepository {

    suspend fun updateContacts(
    )
}