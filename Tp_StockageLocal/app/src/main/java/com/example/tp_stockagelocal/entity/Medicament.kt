package com.example.tp_stockagelocal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medications")
data class Medication(
    @PrimaryKey(autoGenerate = true)
    val medicationId: Int? = null,
    val name: String,
    val dosage: String,
    val frequency: String,
    val instructions: String?
)

