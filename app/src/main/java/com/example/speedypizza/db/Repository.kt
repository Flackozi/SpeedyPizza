package com.example.speedypizza.db

import com.example.speedypizza.entity.User


class Repository(private val dao: UserDAO) {


    fun login(username: String, password: String): User {

        return dao.login(username, password)

    }


}


