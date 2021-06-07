package jafari.alireza.contacts.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jafari.alireza.contacts.model.source.local.AppDatabase
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    internal fun provideDatabase(
        @ApplicationContext context: Context,
        @Named("dbName") dbName: String
    ) = Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()


    @Provides
    @Singleton
    internal fun provideExploreDao(appDatabase: AppDatabase) = appDatabase.listDao

    @Provides
    @Singleton
    internal fun provideDetailsDao(appDatabase: AppDatabase) = appDatabase.detailsDao

    @Provides
    @Singleton
    internal fun provideContactDao(appDatabase: AppDatabase) = appDatabase.contactDao

    @Provides
    @Singleton
    @Named("dbName")
    fun provideDatabaseName() = "contacts.db"


}
