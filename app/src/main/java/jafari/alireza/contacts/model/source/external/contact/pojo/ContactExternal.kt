package jafari.alireza.contacts.model.source.external.contact.pojo

import android.net.Uri
import jafari.alireza.contacts.model.domain.list.ListModel
import jafari.alireza.contacts.model.source.local.contact.entity.ContactEntity

data class ContactExternal(
    val contactId: Long,
    val name: String,
    val phoneNumber: List<String>,
    val avatar: Uri?
)

fun ContactExternal.asDatabaseEntity() =
    ContactEntity(
        contactId = contactId,
        name = name,
        phoneNumber = phoneNumber,
        avatar = avatar?.toString()

    )

fun List<ContactExternal>.asDatabaseEntities(): List<ContactEntity> {
    return map {
        it.asDatabaseEntity()
    }
}

fun ContactExternal.asListModel() =
    ListModel(
        contactId = contactId,
        name = name,
        avatar = avatar

    )


fun List<ContactExternal>.asListModels(): List<ListModel> {
    return map {
        it.asListModel()
    }
}