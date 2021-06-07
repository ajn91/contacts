package jafari.alireza.contacts.model.source.local.list.datasource


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import jafari.alireza.contacts.model.source.local.contact.entity.ContactEntity


@Dao
interface ListDao {

    @Query("SELECT * FROM contacts ORDER BY name")
    fun getAllLive(): LiveData<List<ContactEntity>>


}
