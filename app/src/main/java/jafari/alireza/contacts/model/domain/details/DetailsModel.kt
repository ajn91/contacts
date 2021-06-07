package jafari.alireza.contacts.model.domain.details

import android.net.Uri

data class DetailsModel(
    val contactId: Long,
    val name: String,
    val phoneNumber: List<String>?,
    val avatar: Uri?
)