package com.example.tp_stockagelocal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tp_stockagelocal.entity.Medication
import com.example.tp_stockagelocal.entity.Prescription
import com.example.tp_stockagelocal.entity.PrescriptionMedication
import com.example.tp_stockagelocal.entity.Patient
import com.example.tp_stockagelocal.utils.Converters


@Database(entities = [
    Patient::class,
    Prescription::class,
    Medication::class,
    PrescriptionMedication::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun prescriptionDao(): PrescriptionDao
    abstract fun medicationDao(): MedicationDao
    abstract fun prescriptionMedicationDao(): PrescriptionMedicationDao
    abstract fun userDao(): PatientDao


    companion object {
        private var INSTANCE: AppDatabase? = null
        fun buildDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) { synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java,
                "medical_database").fallbackToDestructiveMigration().build()
            }
            }
            return INSTANCE
        }
    }

    }

