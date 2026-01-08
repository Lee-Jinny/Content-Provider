package com.jinnylee.customgallery.core.di

import android.content.Context
import com.jinnylee.customgallery.data.repository.GalleryRepositoryImpl
import com.jinnylee.customgallery.domain.repository.GalleryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGalleryRepository(
        galleryRepositoryImpl: GalleryRepositoryImpl
    ): GalleryRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideContentResolver(@ApplicationContext context: Context) =
        context.contentResolver
}