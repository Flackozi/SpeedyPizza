package com.example.speedypizza.db

import android.util.Log
import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.entity.User


class Repository(private val dao: UserDAO) {


    fun login(username: String, password: String): User {

        return dao.login(username, password)

    }
    fun SendConstraint(
        nickname: String,
        lunedi: Int,
        martedi: Int,
        mercoledi: Int,
        giovedi: Int,
        venerdi: Int,
        sabato: Int,
        domenica: Int
    ) {
//        dao.sendConstraint(nickname, lunedi, martedi, mercoledi, giovedi, venerdi, sabato, domenica)
        Log.d("prova", nickname + lunedi)
        val constraints = Constraints(nickname, 4, 5, lunedi, martedi, mercoledi, giovedi, venerdi, sabato, domenica)
        Log.d("provadopo", constraints.nickname)
        dao.sendConstraint(constraints)
    }

    fun retrieveMyRider(): List<User> {

        return dao.retrieveMyRiders()

    }

    fun deleteRider(username: String){
         return dao.deleteRider(username)
    }

    fun addRider(username: String){
        return dao.addRider(username)
    }

}


