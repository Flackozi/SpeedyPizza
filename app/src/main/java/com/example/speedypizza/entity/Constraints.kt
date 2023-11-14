package com.example.speedypizza.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Constraints")
data class Constraints(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nickname: String,
    val lunedi: Int,
    val martedi: Int,
    val mercoledi: Int,
    val giovedi: Int,
    val venerdi: Int,
    val sabato: Int,
    val domenica: Int
)
