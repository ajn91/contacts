package jafari.alireza.contacts.model.domain.list

import android.net.Uri

data class ListModel(
    val contactId: Long,
    val name: String,
    val avatar: Uri?
)