package com.unallapps.tko.data.repository

import com.unallapps.tko.data.entity.User

interface UserRepository {

    suspend fun insert(user:User)

    suspend fun getAllUser(email: String, pass: String): User?

    suspend fun getAllUserEmail(email: String): User?
}