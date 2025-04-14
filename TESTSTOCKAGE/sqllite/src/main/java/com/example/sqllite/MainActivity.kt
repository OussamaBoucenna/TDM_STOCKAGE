package com.example.sqllite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.sqllite.screen.UserListScreen
import com.example.sqllite.ui.theme.TESTSTOCKAGETheme
import com.example.sqllite.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TESTSTOCKAGETheme {
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

