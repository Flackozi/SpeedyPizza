package com.example.speedypizza.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDAO {

    @Insert
    fun insertUser(user: User)

    @Query("SELECT role FROM User WHERE nickname = :username and password = :password")
    fun login(username: String, password: String): Int

    @Query("SELECT role FROM User WHERE nickname = :username and password = :password")
    fun login1(username: String, password: String): Int

    @Query("SELECT * FROM User WHERE nickname = :username")
    fun getUserInfo(username: String): User




}