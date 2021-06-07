package jafari.alireza.contacts.model.source.local.contact.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import jafari.alireza.contacts.model.domain.details.DetailsModel
import jafari.alireza.contacts.model.domain.list.ListModel

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey
    val contactId: Long,
    val name: String,
    val phoneNumber: List<String>?,
    val avatar: String?
)

fun ContactEntity.asListModel() =
    ListModel(
        contactId = contactId,
        name = name,
        avatar = if (avatar != null) Uri.parse(avatar) else null

    )

fun ContactEntity.asDetailsModel() =
    DetailsModel(
        contactId = contactId,
        name = name,
        phoneNumber = phoneNumber,
        avatar = if (avatar != null) Uri.parse(avatar) else null

    )

fun List<ContactEntity>.asListModels(): List<ListModel> {
    return map {
        it.asListModel()
    }
}