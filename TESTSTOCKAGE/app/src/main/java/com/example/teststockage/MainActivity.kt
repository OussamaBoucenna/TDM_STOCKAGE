package com.example.teststockage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.teststockage.screen.UserListScreen
import com.example.teststockage.ui.theme.ExampleRoomTheme

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
    val userViewModel = UserViewModel(application.userRepository)
    UserListScreen(userViewModel )
}

