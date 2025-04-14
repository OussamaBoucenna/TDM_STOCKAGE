package com.example.teststockage.screen

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
import com.example.teststockage.User
import com.example.teststockage.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserListScreen(userViewModel: UserViewModel) {
    val users = userViewModel.users.value

    LaunchedEffect(true) {
        userViewModel.getUsers()
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                userViewModel.addUser(User(firstName = "John", lastName = "Doe"))
            }) {
                Icon(Icons.Default.Add, "Add")
            }
        }
    ) {
        LazyColumn {
            items(users) { user ->
                UserItem(
                    user = user,
                    onDelete = { userViewModel.deleteUser(user) }
                )
            }
        }
    }
}

@Composable
fun UserItem(user: User, onDelete: () -> Unit) {
    Card {
        Row(Modifier.padding(16.dp)) {
            Column(Modifier.weight(1f)) {
                Text(user.firstName, style = MaterialTheme.typography.bodyLarge)
                Text(user.lastName)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, "Delete")
            }
        }
    }
}

