package jafari.alireza.contacts.model.source.local.details.datasource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import jafari.alireza.contacts.model.source.local.contact.entity.ContactEntity

@Dao
interface DetailsDao {
    @Query("select * from contacts WHERE contactId = :id")
    fun getDetails(id: Long): LiveData<ContactEntity?>
}
