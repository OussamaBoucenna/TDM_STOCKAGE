package com.example.teststockage

import com.example.teststockage.room.UserDao

class UserRepository(private val userDao: UserDao)  {

    suspend fun addUser(user: User) = userDao.insertUser(user)

    suspend fun getAllUsers() = userDao.getAllUsers()
    suspend fun getUserById(id: Int) = userDao.getUserById(id)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)

}