package jafari.alireza.contacts.model.source.local.list.datasource


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity


@Dao
interface ListDao:ContactDao {

    @Query("SELECT * FROM contacts")
    fun getAllLive(): LiveData<List<ContactEntity>>




}
