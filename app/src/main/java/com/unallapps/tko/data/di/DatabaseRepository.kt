package com.unallapps.tko.data.di

import android.content.Context
import androidx.room.Room
import com.unallapps.tko.data.AppDatabase
import com.unallapps.tko.data.dao.UserDao
import com.unallapps.tko.data.repository.UserRepository
import com.unallapps.tko.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseRepository {

    @Provides
    @Singleton
    fun provideCreateDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    @Provides
    @Singleton
    fun provideUser(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository =
        userRepositoryImpl

}