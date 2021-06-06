package jafari.alireza.contacts.model.source.local.list.datasource

import android.Manifest
import androidx.annotation.RequiresPermission
import jafari.alireza.contacts.model.source.external.list.pojo.ContactExternal
import javax.inject.Inject

class ListExternalDataSourceImp @Inject constructor(
     val contactProvider: ContactProvider
) : ListExternalDataSource {

    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    override suspend fun getContacts(): List<ContactExternal> {
       return contactProvider.retrieveAllContacts()
    }


}