package com.example.realmdatabase

import androidx.lifecycle.asLiveData
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.map

class Repository {
    private val db = MyApp.realm

       val users = db.query<User>().asFlow().map { results ->
           results.list.toList()
       }.asLiveData()

    suspend fun addUser(user:User){
        db.write {
            copyToRealm(user, updatePolicy = UpdatePolicy.ALL)
        }
    }
    suspend fun deleteUser(user: User){
        db.write {
            val userToDelete = findLatest(user) ?: return@write
            delete(userToDelete)
        }
    }
    suspend fun updateUser(user :User){
        db.write {
          copyToRealm(user, updatePolicy = UpdatePolicy.ALL)

        }
    }
}