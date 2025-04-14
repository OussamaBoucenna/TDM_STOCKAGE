package com.example.tp_stockagelocal.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tp_stockagelocal.entity.Medication
import com.example.tp_stockagelocal.entity.Prescription
import com.example.tp_stockagelocal.repository.PrescriptionRepository
import androidx.lifecycle.asLiveData


class PrescriptionViewModel(private val repository: PrescriptionRepository) : ViewModel() {
    val allPrescriptions = repository.getAllPrescriptions().asLiveData()

    fun getPrescriptionsByPatient(patientId: String) =
        repository.getPrescriptionsByPatient(patientId).asLiveData()

    suspend fun savePrescription(
        prescription: Prescription,
        medications: List<Medication>,
        specialInstructions: Map<Int, String?> = emptyMap()
    ) {
        repository.savePrescriptionWithMedications(prescription, medications, specialInstructions)
    }

    suspend fun getPrescriptionWithMedications(prescriptionId: Int) =
        repository.getPrescriptionWithMedications(prescriptionId)

    suspend fun updatePrescription(prescription: Prescription) {
        repository.updatePrescription(prescription)
    }

    suspend fun deletePrescription(prescription: Prescription) {
        repository.deletePrescription(prescription)
    }
}