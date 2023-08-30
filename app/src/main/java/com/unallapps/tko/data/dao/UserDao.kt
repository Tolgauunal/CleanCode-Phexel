package com.unallapps.tko.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.unallapps.tko.data.entity.User


@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM USER WHERE email = :email AND pass = :pass")
    suspend fun getUserByEmail(email: String, pass: String): User

    @Query("SELECT * FROM USER WHERE email = :email")
    suspend fun getUserByEmaill(email: String): User
}