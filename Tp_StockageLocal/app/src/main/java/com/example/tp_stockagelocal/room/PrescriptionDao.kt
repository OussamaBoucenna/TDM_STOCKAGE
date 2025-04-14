package com.example.tp_stockagelocal.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tp_stockagelocal.entity.Prescription
import kotlinx.coroutines.flow.Flow

@Dao
interface PrescriptionDao {
    @Query("SELECT * FROM Prescription ORDER BY date DESC")
    fun getAllPrescriptions(): Flow<List<Prescription>>

    @Query("SELECT * FROM Prescription WHERE prescriptionId = :id")
    suspend fun getPrescriptionById(id: Int): Prescription?

    @Query("SELECT * FROM Prescription WHERE patientId = :patientId ORDER BY date DESC")
    fun getPrescriptionsByPatient(patientId: String): Flow<List<Prescription>>

    @Insert
    suspend fun insertPrescription(prescription: Prescription): Long

    @Update
    suspend fun updatePrescription(prescription: Prescription)

    @Delete
    suspend fun deletePrescription(prescription: Prescription)
}
