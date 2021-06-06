package jafari.alireza.contacts.model.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jafari.alireza.contacts.model.source.local.details.datasource.DetailsDao
import jafari.alireza.contacts.model.source.local.list.datasource.ContactProvider


@Database(
    entities = [SampleEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(

)

abstract class AppDatabase : RoomDatabase() {
    abstract val listDao: ContactProvider
    abstract val detailsDao: DetailsDao
}


