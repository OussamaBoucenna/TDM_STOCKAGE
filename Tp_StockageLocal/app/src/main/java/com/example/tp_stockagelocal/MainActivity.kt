package com.example.tp_stockagelocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.tp_stockagelocal.screen.UserListScreen
import com.example.tp_stockagelocal.ui.theme.ExampleRoomTheme
import com.example.tp_stockagelocal.viewmodel.PatientViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExampleRoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  MyApp()
                  //Screen5()
                }
            }
        }
    }
}


@Composable
fun MyApp() {
    val context = LocalContext.current
    val application = context.applicationContext as MyApplication
    val patientViewModel = PatientViewModel(application.patientRepository)
    UserListScreen(patientViewModel )
}

