package com.example.tp_stockagelocal.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_stockagelocal.entity.Patient
import com.example.tp_stockagelocal.repository.PatientRepository
import kotlinx.coroutines.launch

class PatientViewModel(private val patientRepository: PatientRepository):ViewModel()  {

    val users = mutableStateOf(emptyList<Patient>())


    fun getUsers() {
        viewModelScope.launch {
            users.value =  patientRepository.getAllUsers()
        }
    }

    fun addUser(patient: Patient) {
        viewModelScope.launch {
            patientRepository.addUser(patient)
            getUsers()
        }
    }

    fun deleteUser(patient: Patient) {
        viewModelScope.launch {
            patientRepository.deleteUser(patient)
            getUsers()
        }
    }



}

