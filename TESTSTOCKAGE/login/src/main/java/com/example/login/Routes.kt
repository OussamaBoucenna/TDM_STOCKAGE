package com.example.login

sealed class Routes (val route: String) {
     object MainScreen  : Routes("main")
     object LoginScreen : Routes("login")
}