package com.example.teststockage

import android.app.Application

class MyApplication: Application() {

    private val database by lazy { AppDatabase.buildDatabase(this) }
    private val userDao by lazy { database?.userDao() }
    val userRepository by lazy { UserRepository(userDao!!) }

}