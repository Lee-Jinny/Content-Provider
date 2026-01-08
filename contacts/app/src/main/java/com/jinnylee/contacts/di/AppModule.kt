package com.jinnylee.contacts.di

import android.content.Context
import androidx.room.Room
import com.jinnylee.contacts.data.database.ContactDao
import com.jinnylee.contacts.data.database.ContactDatabase
import com.jinnylee.contacts.data.datasource.ContactsDataSource
import com.jinnylee.contacts.data.repository.MainRepositoryImpl
import com.jinnylee.contacts.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContactDatabase(@ApplicationContext context: Context): ContactDatabase {
        return Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            "contact_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactDao(database: ContactDatabase): ContactDao {
        return database.contactDao()
    }

    @Provides
    @Singleton
    fun provideContactsDataSource(@ApplicationContext context: Context): ContactsDataSource {
        return ContactsDataSource(context.contentResolver)
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        dataSource: ContactsDataSource,
        dao: ContactDao
    ): MainRepository {
        return MainRepositoryImpl(dataSource, dao)
    }
}
