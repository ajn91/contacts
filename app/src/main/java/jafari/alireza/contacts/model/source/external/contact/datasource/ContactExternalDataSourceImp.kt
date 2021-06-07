package jafari.alireza.contacts.model.source.local.list.datasource

import android.Manifest
import androidx.annotation.RequiresPermission
import jafari.alireza.contacts.model.source.external.contact.pojo.ContactExternal
import javax.inject.Inject

class ContactExternalDataSourceImp @Inject constructor(
     val contactProvider: ContactProvider
) : ContactExternalDataSource {

    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    override suspend fun getContacts(): List<ContactExternal> {
       return contactProvider.retrieveAllContacts()
    }


}