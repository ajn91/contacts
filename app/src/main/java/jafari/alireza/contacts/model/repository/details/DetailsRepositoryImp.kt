package jafari.alireza.contacts.model.repository.details

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.contacts.model.source.local.details.datasource.DetailsLocalDataSource


import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    private val localDataSource: DetailsLocalDataSource,
    @ApplicationContext val context: Context
) : DetailsRepository {




}