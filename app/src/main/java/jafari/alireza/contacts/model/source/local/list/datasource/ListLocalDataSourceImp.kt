package jafari.alireza.contacts.model.source.local.list.datasource

import javax.inject.Inject

class ListLocalDataSourceImp @Inject constructor(
    private val listDao: ListDao
) : ListLocalDataSource {


}