package com.example.tp_stockagelocal.repository

import com.example.tp_stockagelocal.entity.Medication
import com.example.tp_stockagelocal.entity.Prescription
import com.example.tp_stockagelocal.entity.PrescriptionMedication
import com.example.tp_stockagelocal.room.MedicationDao
import com.example.tp_stockagelocal.room.PrescriptionDao
import com.example.tp_stockagelocal.room.PrescriptionMedicationDao
import kotlinx.coroutines.flow.first

class PrescriptionRepository(
    private val prescriptionDao: PrescriptionDao,
    private val medicationDao: MedicationDao,
    private val prescriptionMedicationDao: PrescriptionMedicationDao
) {
    // Fonctions pour les prescriptions
    fun getAllPrescriptions() = prescriptionDao.getAllPrescriptions()
    fun getPrescriptionsByPatient(patientId: String) = prescriptionDao.getPrescriptionsByPatient(patientId)

    suspend fun getPrescriptionWithMedications(prescriptionId: Int): Pair<Prescription?, List<Medication>> {
        val prescription = prescriptionDao.getPrescriptionById(prescriptionId)
        val medications = if (prescription != null) {
            prescriptionMedicationDao.getMedicationsByPrescriptionId(prescriptionId).first()
        } else {
            emptyList()
        }
        return Pair(prescription, medications)
    }

    suspend fun savePrescriptionWithMedications(
        prescription: Prescription,
        medications: List<Medication>,
        specialInstructions: Map<Int, String?> = emptyMap()
    ) {
        val prescriptionId = prescriptionDao.insertPrescription(prescription).toInt()

        medications.forEach { medication ->
            val medicationId = medicationDao.insertMedication(medication).toInt()
            val instructions = specialInstructions[medication.medicationId] ?: ""
            val link = PrescriptionMedication(
                prescriptionId = prescriptionId,
                medicationId = medicationId,
                specialInstructions = instructions
            )
            prescriptionMedicationDao.linkPrescriptionAndMedication(link)
        }
    }

    suspend fun updatePrescription(prescription: Prescription) {
        prescriptionDao.updatePrescription(prescription)
    }

    suspend fun deletePrescription(prescription: Prescription) {
        prescriptionDao.deletePrescription(prescription)
    }
}