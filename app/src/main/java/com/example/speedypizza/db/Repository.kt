package com.example.speedypizza.db

import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.entity.User
import com.example.speedypizza.screens.rider.GlobalVariables


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
        println(nickname)
        println(lunedi)
        println(martedi)
        println(mercoledi)
        println(giovedi)
        println(venerdi)
        println(sabato)
        println(domenica)

        dao.sendConstraint(Constraints(nickname, lunedi, martedi, mercoledi, giovedi, venerdi, sabato, domenica))
    }

    fun retrieveMyRider(): List<User> {

        return dao.retrieveMyRiders()

    }

    fun deleteRider(username: String){
         return dao.deleteRider(username)
    }

    fun getPhone(nickname: String): String? {
        return dao.getPhone(nickname)
    }


}


