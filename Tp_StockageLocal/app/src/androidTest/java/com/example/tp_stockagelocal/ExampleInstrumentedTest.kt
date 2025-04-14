package com.example.tp_stockagelocal

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tp_stockagelocal.entity.Medication
import com.example.tp_stockagelocal.entity.Prescription
import com.example.tp_stockagelocal.entity.PrescriptionMedication
import com.example.tp_stockagelocal.entity.Patient
import com.example.tp_stockagelocal.room.AppDatabase
import com.example.tp_stockagelocal.room.PatientDao
import com.example.tp_stockagelocal.room.MedicationDao
import com.example.tp_stockagelocal.room.PrescriptionDao
import com.example.tp_stockagelocal.room.PrescriptionMedicationDao
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import java.util.Date

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var prescriptionDao: PrescriptionDao
    private lateinit var patientDao: PatientDao
    private lateinit var medicationDao: MedicationDao
    private lateinit var prescriptionMedicationDao: PrescriptionMedicationDao
    private lateinit var db: AppDatabase

    @Before
    fun initDB() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            appContext, AppDatabase::class.java
        ).allowMainThreadQueries().build()

        patientDao = db.userDao()
        prescriptionDao = db.prescriptionDao()
        medicationDao = db.medicationDao()
        prescriptionMedicationDao = db.prescriptionMedicationDao()

    }

    @After
    fun closeDB() {
        db.close()
    }


    @Test
    fun testInsertAndGetUser() {
        val patient1 =
            Patient(id = 1,firstName = "tuto1", lastName = "tuto2")
        runBlocking {
            patientDao.insertUser(patient1)
            val list = patientDao.getAllUsers()
            assertEquals(patient1, list[0])
        }

    }

    @Test
    fun insertAndGetPrescription() = runBlocking {
        // Création d'une prescription de test
        val prescription = Prescription(
            patientId = "P12345",
            doctorId = "D9876",
            date = Date(),
        )

        // Insertion dans la base de données
        val prescriptionId = prescriptionDao.insertPrescription(prescription).toInt()

        // Récupération de la prescription
        val loadedPrescription = prescriptionDao.getPrescriptionById(prescriptionId)

        // Vérification
        assertNotNull(loadedPrescription)
        assertEquals(prescription.patientId, loadedPrescription?.patientId)
        assertEquals(prescription.doctorId, loadedPrescription?.doctorId)
    }

    @Test
    fun insertAndGetMedication() = runBlocking {
        // Création d'un médicament de test
        val medication = Medication(
            name = "Paracétamol",
            dosage = "500mg",
            frequency = "3 fois par jour",
            instructions = "À prendre avec de l'eau"
        )

        // Insertion dans la base de données
        val medicationId = medicationDao.insertMedication(medication).toInt()

        // Récupération du médicament
        val loadedMedication = medicationDao.getMedicationById(medicationId)

        // Vérification
        assertNotNull(loadedMedication)
        assertEquals(medication.name, loadedMedication?.name)
        assertEquals(medication.dosage, loadedMedication?.dosage)
        assertEquals(medication.frequency, loadedMedication?.frequency)
        assertEquals(medication.instructions, loadedMedication?.instructions)
    }

    @Test
    fun linkPrescriptionAndMedication() = runBlocking {
        // Création d'une prescription et d'un médicament
        val prescription = Prescription(
            patientId = "P12345",
            doctorId = "D9876",
            date = Date(),
        )

        val medication = Medication(
            name = "Amoxicilline",
            dosage = "1g",
            frequency = "2 fois par jour",
            instructions = "À prendre pendant les repas"
        )

        // Insertion dans la base de données
        val prescriptionId = prescriptionDao.insertPrescription(prescription).toInt()
        val medicationId = medicationDao.insertMedication(medication).toInt()

        // Création du lien
        val link = PrescriptionMedication(
            prescriptionId = prescriptionId,
            medicationId = medicationId,
            specialInstructions = "Prendre pendant 7 jours"
        )

        prescriptionMedicationDao.linkPrescriptionAndMedication(link)

        // Récupération des médicaments liés à la prescription
        val medicationLinks = prescriptionMedicationDao.getMedicationsForPrescription(prescriptionId)

        // Vérification
        assertEquals(1, medicationLinks.size)
        assertEquals(prescriptionId, medicationLinks[0].prescriptionId)
        assertEquals(medicationId, medicationLinks[0].medicationId)
        assertEquals("Prendre pendant 7 jours", medicationLinks[0].specialInstructions)
    }

    @Test
    fun testGetMedicationsByPrescriptionId() = runBlocking {
        // Création d'une prescription et de deux médicaments
        val prescription = Prescription(
            patientId = "P12345",
            doctorId = "D9876",
            date = Date(),
        )

        val medication1 = Medication(
            name = "Ibuprofène",
            dosage = "400mg",
            frequency = "3 fois par jour",
            instructions = "En cas de douleur"
        )

        val medication2 = Medication(
            name = "Oméprazole",
            dosage = "20mg",
            frequency = "1 fois par jour",
            instructions = "Le matin à jeun"
        )

        // Insertion dans la base de données
        val prescriptionId = prescriptionDao.insertPrescription(prescription).toInt()
        val medicationId1 = medicationDao.insertMedication(medication1).toInt()
        val medicationId2 = medicationDao.insertMedication(medication2).toInt()

        // Création des liens
        prescriptionMedicationDao.linkPrescriptionAndMedication(
            PrescriptionMedication(
                prescriptionId = prescriptionId,
                medicationId = medicationId1,
                specialInstructions = null
            )
        )

        prescriptionMedicationDao.linkPrescriptionAndMedication(
            PrescriptionMedication(
                prescriptionId = prescriptionId,
                medicationId = medicationId2,
                specialInstructions = null
            )
        )

        // Récupération des médicaments liés à la prescription
        val medications = prescriptionMedicationDao.getMedicationsByPrescriptionId(prescriptionId).first()

        // Vérification
        assertEquals(2, medications.size)

        // Vérification du premier médicament
        val loadedMedication1 = medications.find { it.name == "Ibuprofène" }
        assertNotNull(loadedMedication1)
        assertEquals("400mg", loadedMedication1?.dosage)

        // Vérification du second médicament
        val loadedMedication2 = medications.find { it.name == "Oméprazole" }
        assertNotNull(loadedMedication2)
        assertEquals("20mg", loadedMedication2?.dosage)
    }


}