package jafari.alireza.contacts.model.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jafari.alireza.contacts.model.source.local.details.datasource.DetailsDao
import jafari.alireza.contacts.model.source.local.list.converter.AvatarTypeConverter
import jafari.alireza.contacts.model.source.local.list.converter.PhoneNumberTypeConverter
import jafari.alireza.contacts.model.source.local.list.datasource.ContactDao
import jafari.alireza.contacts.model.source.local.list.datasource.ContactProvider
import jafari.alireza.contacts.model.source.local.list.datasource.ListDao
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity


@Database(
    entities = [ContactEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
PhoneNumberTypeConverter::class,
    AvatarTypeConverter::class
)

abstract class AppDatabase : RoomDatabase() {
    abstract val listDao: ListDao
    abstract val detailsDao: DetailsDao
    abstract val contactDao: ContactDao
}


