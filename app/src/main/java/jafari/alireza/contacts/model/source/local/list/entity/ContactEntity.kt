package jafari.alireza.contacts.model.source.local.list.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import jafari.alireza.contacts.model.domain.list.ListModel

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey
    val contactId: Long,
    val name: String,
    val phoneNumber: List<String>,
    val avatar: Uri?
)
fun ContactEntity.asListModel() =
    ListModel(
       contactId = contactId,
        name = name,
        avatar = avatar

    )


fun List<ContactEntity>.asListModels(): List<ListModel> {
    return map {
        it.asListModel()
    }
}