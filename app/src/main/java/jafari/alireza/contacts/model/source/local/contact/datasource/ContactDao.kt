package jafari.alireza.contacts.model.source.local.list.datasource


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity


@Dao
interface ContactDao {


    @Query("SELECT * FROM contacts")
    fun getAll(): List<ContactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend   fun insert(contact: ContactEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend   fun insertAll(contactList: List<ContactEntity>): List<Long>

    @Query("DELETE FROM contacts")
    suspend   fun deleteAll()

}
