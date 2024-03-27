package com.example.realmdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    val repository = Repository()
    val users = repository.users
    fun addUser(user: User){
        viewModelScope.launch {
        repository.addUser(user)
        }
    }

    fun getAllUsers(): LiveData<List<User>> {
        return users
    }
    fun updateUser(user:User){
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }
    fun deleteUser(user: User): Boolean
    {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
        return true
    }
}