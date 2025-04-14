package com.example.sqllite.repository

import com.example.sqllite.entity.User
import com.example.sqllite.room.UserDao

class UserRepository(private val userDao: UserDao)  {

    suspend fun addUser(user: User) = userDao.insertUser(user)

    suspend fun getAllUsers() = userDao.getAllUsers()
    suspend fun getUserById(id: Int) = userDao.getUserById(id)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)

}