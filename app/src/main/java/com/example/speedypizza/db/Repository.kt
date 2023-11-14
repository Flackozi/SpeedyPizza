package com.example.speedypizza.db

import com.example.speedypizza.entity.User


class Repository(private val dao: UserDAO) {


    fun login(username: String, password: String): User {

        return dao.login(username, password)

    }
    fun SendConstraint(
        lunedi: Int,
        martedi: Int,
        mercoledi: Int,
        giovedi: Int,
        venerdi: Int,
        sabato: Int,
        domenica: Int
    ){
        dao.sendConstraint(lunedi, martedi, mercoledi, giovedi, venerdi, sabato, domenica)

    }

    fun retrieveMyRider(): List<User> {

        return dao.retrieveMyRiders()

    }


}


