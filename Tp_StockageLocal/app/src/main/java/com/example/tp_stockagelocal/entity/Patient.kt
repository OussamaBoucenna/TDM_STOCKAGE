package com.example.tp_stockagelocal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Patient (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val firstName:String,
    val lastName:String
)