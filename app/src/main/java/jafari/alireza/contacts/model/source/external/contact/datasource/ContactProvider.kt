package jafari.alireza.contacts.model.source.external.contact.datasource


import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import androidx.annotation.RequiresPermission
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.contacts.model.source.external.contact.pojo.ContactExternal
import javax.inject.Inject

class ContactProvider @Inject constructor(@ApplicationContext val context: Context) {

    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    fun isContactExists(
        phoneNumber: String
    ): Boolean {
        val lookupUri = Uri.withAppendedPath(
            ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
            Uri.encode(phoneNumber)
        )
        val projection = arrayOf(
            ContactsContract.PhoneLookup._ID,
            ContactsContract.PhoneLookup.NUMBER,
            ContactsContract.PhoneLookup.DISPLAY_NAME
        )
        context.contentResolver.query(lookupUri, projection, null, null, null).use {
            return (it?.moveToFirst() == true)
        }
    }

    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    fun retrieveAllContacts(
        searchPattern: String = "",
        retrieveAvatar: Boolean = true,
        limit: Int = -1,
        offset: Int = -1
    ): List<ContactExternal> {
        val result: MutableList<ContactExternal> = mutableListOf()
        context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            CONTACT_PROJECTION,
            if (searchPattern.isNotBlank()) "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} LIKE '%?%'" else null,
            if (searchPattern.isNotBlank()) arrayOf(searchPattern) else null,
            if (limit > 0 && offset > -1) "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} ASC LIMIT $limit OFFSET $offset"
            else ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " ASC"
        )?.use {
            if (it.moveToFirst()) {
                do {
                    val contactId = it.getLong(it.getColumnIndex(CONTACT_PROJECTION[0]))
                    val name = it.getString(it.getColumnIndex(CONTACT_PROJECTION[2])) ?: ""
                    val hasPhoneNumber =
                        it.getString(it.getColumnIndex(CONTACT_PROJECTION[3])).toInt()
                    val phoneNumber: List<String> = if (hasPhoneNumber > 0) {
                        retrievePhoneNumber(contactId)
                    } else mutableListOf()

                    val avatar = if (retrieveAvatar) retrieveAvatar(contactId) else null
                    result.add(ContactExternal(contactId, name, phoneNumber, avatar))
                } while (it.moveToNext())
            }
        }
        return result
    }

    private fun retrievePhoneNumber(contactId: Long): List<String> {
        val result: MutableList<String> = mutableListOf()
        context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} =?",
            arrayOf(contactId.toString()),
            null
        )?.use {
            if (it.moveToFirst()) {
                do {
                    val phone =
                        it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    if (!result.contains(phone))
                        result.add(phone)
                } while (it.moveToNext())
            }
        }
        return result
    }

    private fun retrieveAvatar(contactId: Long): Uri? {
        return context.contentResolver.query(
            ContactsContract.Data.CONTENT_URI,
            null,
            "${ContactsContract.Data.CONTACT_ID} =? AND ${ContactsContract.Data.MIMETYPE} = '${ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE}'",
            arrayOf(contactId.toString()),
            null
        )?.use {
            if (it.moveToFirst()) {
                val contactUri = ContentUris.withAppendedId(
                    ContactsContract.Contacts.CONTENT_URI,
                    contactId
                )
                Uri.withAppendedPath(
                    contactUri,
                    ContactsContract.Contacts.Photo.CONTENT_DIRECTORY
                )
            } else null
        }
    }

    companion object {

        val CONTACT_PROJECTION = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
        )
    }
}

