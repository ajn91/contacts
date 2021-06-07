package jafari.alireza.contacts.di.module.base


import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import jafari.alireza.contacts.model.repository.contact.ContactRepository
import jafari.alireza.contacts.model.repository.contact.ContactRepositoryImp
import jafari.alireza.contacts.model.repository.list.ListRepository
import jafari.alireza.contacts.model.repository.list.ListRepositoryImp
import jafari.alireza.contacts.model.source.local.list.datasource.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal interface BaseModule {

    @Singleton
    @Binds
    fun bindRepository(
        input: ContactRepositoryImp
    ): ContactRepository



    @Singleton
    @Binds
    fun bindLocalDataSource(
        input: ContactLocalDataSourceImp
    ): ContactLocalDataSource


    @Singleton
    @Binds
    fun bindExternalDataSource(
        input: ContactExternalDataSourceImp
    ): ContactExternalDataSource
}