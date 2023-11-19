package com.example.speedypizza.entity

import androidx.room.PrimaryKey


data class Days(
    @PrimaryKey
    val day: String,
    val min: Int,
    val max: Int
)