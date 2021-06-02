package jafari.alireza.contacts.di.module.list


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import jafari.alireza.contacts.model.repository.list.ListRepository
import jafari.alireza.contacts.model.repository.list.ListRepositoryImp
import jafari.alireza.contacts.model.source.local.list.datasource.ListLocalDataSource
import jafari.alireza.contacts.model.source.local.list.datasource.ListLocalDataSourceImp


@Module
@InstallIn(ViewModelComponent::class)
internal interface ListModule {

    @ViewModelScoped
    @Binds
    fun bindRepository(
        input: ListRepositoryImp
    ): ListRepository



    @ViewModelScoped
    @Binds
    fun bindLocalDataSource(
        input: ListLocalDataSourceImp
    ): ListLocalDataSource
}