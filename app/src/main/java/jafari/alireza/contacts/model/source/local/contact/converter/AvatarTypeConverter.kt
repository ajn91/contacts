package jafari.alireza.contacts.model.source.local.contact.converter

import android.net.Uri
import androidx.annotation.Keep
import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Keep
class AvatarTypeConverter {

    @TypeConverter
    fun fromString(value: String?) = value?.let { Json.decodeFromString<Uri>(it) }


    @TypeConverter
    fun fromItem(value: Uri?) =value?.let {   Json.encodeToString(value)}


}
