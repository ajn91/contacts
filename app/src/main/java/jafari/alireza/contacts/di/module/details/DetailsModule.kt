package jafari.alireza.contacts.di.module.details


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import jafari.alireza.contacts.model.repository.details.DetailsRepository
import jafari.alireza.contacts.model.repository.details.DetailsRepositoryImp
import jafari.alireza.contacts.model.source.local.details.datasource.DetailsLocalDataSource
import jafari.alireza.contacts.model.source.local.details.datasource.DetailsLocalDataSourceImp


@Module
@InstallIn(ViewModelComponent::class)
internal interface DetailsModule {

    @ViewModelScoped
    @Binds
    fun bindRepository(
        input: DetailsRepositoryImp
    ): DetailsRepository



    @ViewModelScoped
    @Binds
    fun bindLocalDataSource(
        input: DetailsLocalDataSourceImp
    ): DetailsLocalDataSource
}