package com.example.speedypizza.entity

data class ScheduleItem(
    val day: String,
    val rider: List<String>,
    val min: String,
    val max: String
)
