package com.example.speedypizza.entity



import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class User(
    @PrimaryKey
    val nickname: String,
    val name: String,
    val surname: String,
    val password: String,
    val phone: String,
    val email: String,
    val role: Int

)