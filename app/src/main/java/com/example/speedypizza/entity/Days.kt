package com.example.speedypizza.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Days(
    @PrimaryKey
    val day: String,
    val min: Int,
    val max: Int
)