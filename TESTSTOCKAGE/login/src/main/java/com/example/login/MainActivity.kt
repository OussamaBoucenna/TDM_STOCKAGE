package com.example.login

import android.content.Context
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.ui.theme.TESTSTOCKAGETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TESTSTOCKAGETheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginApp()
                }
            }
        }
    }
}

@Composable
fun LoginApp(){
    val navController = rememberNavController()
    val context = LocalContext.current


    val sharedPreferences = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

    val isLoggedIn = sharedPreferences.getBoolean("user_connected", false)
    val startDestination = if (isLoggedIn) Routes.MainScreen.route else Routes.LoginScreen.route

    NavHost(navController = navController , startDestination =  startDestination ) {

         composable(Routes.LoginScreen.route) {
             SignInScreen(onLoginClick = {
                 with(sharedPreferences.edit()) {
                     putBoolean("user_connected", true)
                     apply()
                 }
                 navController.navigate(Routes.MainScreen.route) {
                     popUpTo(Routes.LoginScreen.route) { inclusive = true }
                 }

             })
         }


         composable(Routes.MainScreen.route) {
            MainScreen(
                onDisconnectClick = {
                   with(sharedPreferences.edit()){
                       putBoolean("user_connected",false)
                       apply()
                   }
                    navController.navigate(Routes.LoginScreen.route)

                }
            )
         }
     }

}
