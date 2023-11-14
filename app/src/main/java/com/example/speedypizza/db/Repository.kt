package com.example.speedypizza.db

import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.entity.User


class Repository(private val dao: UserDAO) {


    fun login(username: String, password: String): User {

        return dao.login(username, password)

    }
    fun SendConstraint(
        constraints: Constraints
    ){
        dao.sendConstraint(constraints)

    }

    fun retrieveMyRider(): List<User> {

        return dao.retrieveMyRiders()

    }


}


