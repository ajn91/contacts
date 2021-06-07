package jafari.alireza.contacts.di.module.base


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jafari.alireza.contacts.model.repository.contact.ContactRepository
import jafari.alireza.contacts.model.repository.contact.ContactRepositoryImp
import jafari.alireza.contacts.model.source.external.contact.datasource.ContactExternalDataSource
import jafari.alireza.contacts.model.source.external.contact.datasource.ContactExternalDataSourceImp
import jafari.alireza.contacts.model.source.local.contact.datasource.ContactLocalDataSource
import jafari.alireza.contacts.model.source.local.contact.datasource.ContactLocalDataSourceImp
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
    fun bindContactLocalDataSource(
        input: ContactLocalDataSourceImp
    ): ContactLocalDataSource


    @Singleton
    @Binds
    fun bindContactExternalDataSource(
        input: ContactExternalDataSourceImp
    ): ContactExternalDataSource
}