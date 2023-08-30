package com.unallapps.tko.data.di

import com.unallapps.tko.data.repository.PhotoRepository
import com.unallapps.tko.data.repository.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun providePhotoRepository(photoRepositoryImpl: PhotoRepositoryImpl): PhotoRepository =
        photoRepositoryImpl
}