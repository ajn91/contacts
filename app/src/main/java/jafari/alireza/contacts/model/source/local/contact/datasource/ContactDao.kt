package jafari.alireza.contacts.model.source.local.contact.datasource


import androidx.room.*
import jafari.alireza.contacts.model.source.local.contact.entity.ContactEntity


@Dao
interface ContactDao {

    @Query("SELECT * FROM contacts")
    fun getAll(): List<ContactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend   fun insert(contact: ContactEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contactList: List<ContactEntity>): List<Long>

    @Query("DELETE FROM contacts")
    suspend   fun deleteAll()

    @Delete
    fun delete(items: List<ContactEntity>)

}
