package com.example.tp_stockagelocal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Prescription(
    @PrimaryKey(autoGenerate = true)
    val prescriptionId: Int? = null,
    val patientId: String,
    val doctorId: String,
    val date: Date?,
)