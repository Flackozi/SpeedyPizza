package com.example.speedypizza.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.speedypizza.entity.User


@Dao
interface UserDAO {

    @Insert
    fun insertUser(user: User)

    @Query("SELECT nickname, name, surname, password, phone, email, role  FROM User WHERE nickname = :username and password = :password")
    fun login(username: String, password: String): User

    @Query("SELECT * FROM User WHERE role = 1")
    fun retrieveMyRiders(): List<User>


}