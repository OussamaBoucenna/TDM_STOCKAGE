package com.example.tp_stockagelocal

import android.app.Application
import com.example.tp_stockagelocal.repository.PatientRepository
import com.example.tp_stockagelocal.room.AppDatabase

class MyApplication: Application() {

    private val database by lazy { AppDatabase.buildDatabase(this) }
    private val userDao by lazy { database?.userDao() }
    val patientRepository by lazy { PatientRepository(userDao!!) }

}