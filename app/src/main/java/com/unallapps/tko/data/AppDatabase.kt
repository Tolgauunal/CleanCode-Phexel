package com.unallapps.tko.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unallapps.tko.data.dao.UserDao
import com.unallapps.tko.data.entity.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}