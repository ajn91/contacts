package jafari.alireza.contacts.model.source.external.contact.datasource

import android.Manifest
import androidx.annotation.RequiresPermission
import jafari.alireza.contacts.model.source.external.contact.pojo.ContactExternal
import javax.inject.Inject

class ContactExternalDataSourceImp @Inject constructor(
     val contactProvider: ContactProvider
) : ContactExternalDataSource {

    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    override fun getContacts(): List<ContactExternal> {
        return contactProvider.retrieveAllContacts()
    }


}