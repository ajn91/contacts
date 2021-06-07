package jafari.alireza.contacts.model.source.local.contact.converter

import androidx.annotation.Keep
import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Keep
class PhoneNumberTypeConverter {

    @TypeConverter
    fun fromString(value: String) = Json.decodeFromString<List<String>>(value)


    @TypeConverter
    fun fromItem(items: List<String>) = Json.encodeToString(items)


}
