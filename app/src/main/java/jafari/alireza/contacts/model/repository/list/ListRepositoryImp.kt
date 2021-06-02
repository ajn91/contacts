package jafari.alireza.contacts.model.repository.list

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.contacts.model.source.local.list.datasource.ListLocalDataSource

import javax.inject.Inject

class ListRepositoryImp @Inject constructor(
    private val localDataSource: ListLocalDataSource,
    @ApplicationContext val context: Context
) : ListRepository {


}