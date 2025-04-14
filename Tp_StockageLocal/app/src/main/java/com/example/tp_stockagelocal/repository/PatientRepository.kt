package com.example.tp_stockagelocal.repository

import com.example.tp_stockagelocal.entity.Patient
import com.example.tp_stockagelocal.room.PatientDao

class PatientRepository(private val patientDao: PatientDao)  {

    suspend fun addUser(patient: Patient) = patientDao.insertUser(patient)

    suspend fun getAllUsers() = patientDao.getAllUsers()
    suspend fun getUserById(id: Int) = patientDao.getUserById(id)
    suspend fun deleteUser(patient: Patient) = patientDao.deleteUser(patient)

}