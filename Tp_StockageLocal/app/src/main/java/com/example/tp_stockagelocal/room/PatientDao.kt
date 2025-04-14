package com.example.tp_stockagelocal.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tp_stockagelocal.entity.Patient

@Dao
interface PatientDao {
    @Insert
    suspend fun insertUser(patient: Patient)

    @Query("SELECT * FROM Patient")
    suspend fun getAllUsers(): List<Patient>

    @Query("SELECT * FROM Patient WHERE id = :id")
    suspend fun getUserById(id: Int): Patient

    @Delete
    suspend fun deleteUser(patient: Patient)
}