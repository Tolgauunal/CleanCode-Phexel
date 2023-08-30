package com.unallapps.tko.data.repository

import com.unallapps.tko.data.dao.UserDao
import com.unallapps.tko.data.entity.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun insert(user: User) {
        return userDao.insert(user)
    }


    override suspend fun getAllUser(email: String, pass: String): User {
        return userDao.getUserByEmail(email, pass)
    }

    override suspend fun getAllUserEmail(email: String): User? {
        return userDao.getUserByEmaill(email)
    }
}