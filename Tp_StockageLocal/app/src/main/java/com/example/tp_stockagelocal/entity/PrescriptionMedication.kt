package com.example.tp_stockagelocal.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["prescriptionId", "medicationId"],
    foreignKeys = [
        ForeignKey(
            entity = Prescription::class,
            parentColumns = ["prescriptionId"],
            childColumns = ["prescriptionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Medication::class,
            parentColumns = ["medicationId"],
            childColumns = ["medicationId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class PrescriptionMedication(
    val prescriptionId: Int,
    val medicationId: Int,
    val specialInstructions: String?
)