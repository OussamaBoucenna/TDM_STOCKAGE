package com.example.tp_stockagelocal.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tp_stockagelocal.entity.Medication
import com.example.tp_stockagelocal.entity.PrescriptionMedication
import kotlinx.coroutines.flow.Flow

@Dao
interface PrescriptionMedicationDao {
    @Query("SELECT * FROM PrescriptionMedication WHERE prescriptionId = :prescriptionId")
    suspend fun getMedicationsForPrescription(prescriptionId: Int): List<PrescriptionMedication>

    @Query("SELECT m.* FROM medications m INNER JOIN PrescriptionMedication pm ON m.medicationId = pm.medicationId WHERE pm.prescriptionId = :prescriptionId")
    fun getMedicationsByPrescriptionId(prescriptionId: Int): Flow<List<Medication>>

    @Insert
    suspend fun linkPrescriptionAndMedication(prescriptionMedication: PrescriptionMedication)

    @Delete
    suspend fun unlinkPrescriptionAndMedication(prescriptionMedication: PrescriptionMedication)
}