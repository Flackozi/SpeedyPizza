package com.example.speedypizza.db

import android.util.Log
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class Repository(private val dao: UserDAO) {
    fun insert(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertUser(user)
        }
    }

    fun readInfo(username: String): User {
        return dao.getUserInfo(username)
    }

    fun login(username: String, password: String): Int {
        var id = 0
        Log.i("valuecheck1", username + password);
        CoroutineScope(Dispatchers.IO).launch{
            id = dao.login(username, password)
            Log.i("valueid: ", id.toString());
        }
        return id
    }


    /*  fun login1(username: String, password: String): String {
        var id = ""
        Log.i("valuecheck1", username + password);
        CoroutineScope(Dispatchers.IO).launch{
            id = dao.login1(username, password)
            Log.i("valueemail: ", id);
        }
        Log.i("valueemaildopo: ", id);
        return id
    }*/

    fun login1(username: String, password: String): Int {
        return dao.login1(username,password)

    }

}


