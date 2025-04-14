package com.example.sqllite

import android.app.Application
import com.example.sqllite.repository.UserRepository
import com.example.sqllite.room.AppDatabase

class MyApplication: Application() {

    private val database by lazy { AppDatabase.buildDatabase(this) }
    private val userDao by lazy { database?.userDao() }
    val userRepository by lazy { UserRepository(userDao!!) }

}