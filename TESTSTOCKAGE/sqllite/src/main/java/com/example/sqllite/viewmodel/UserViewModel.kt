package com.example.sqllite.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqllite.entity.User
import com.example.sqllite.repository.UserRepository
import kotlinx.coroutines.launch


class UserViewModel(private val userRepository: UserRepository): ViewModel()  {

    val users = mutableStateOf(emptyList<User>())




    fun getUsers() {
        viewModelScope.launch {
            users.value =  userRepository.getAllUsers()
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userRepository.addUser(user)
            getUsers()
        }

    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
            getUsers()
        }
    }



}

