package com.example.tp_stockagelocal.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp_stockagelocal.entity.Patient
import com.example.tp_stockagelocal.viewmodel.PatientViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserListScreen(patientViewModel: PatientViewModel) {
    val users = patientViewModel.users.value

    LaunchedEffect(true) {
        patientViewModel.getUsers()
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                patientViewModel.addUser(Patient(firstName = "John", lastName = "Doe"))
            }) {
                Icon(Icons.Default.Add, "Add")
            }
        }
    ) {
        LazyColumn {
            items(users) { user ->
                UserItem(
                    patient = user,
                    onDelete = { patientViewModel.deleteUser(user) }
                )
            }
        }
    }
}

@Composable
fun UserItem(patient: Patient, onDelete: () -> Unit) {
    Card {
        Row(Modifier.padding(16.dp)) {
            Column(Modifier.weight(1f)) {
                Text(patient.firstName, style = MaterialTheme.typography.bodyLarge)
                Text(patient.lastName)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, "Delete")
            }
        }
    }
}

