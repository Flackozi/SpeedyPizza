package com.example.speedypizza.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity /*(foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["nickname"],
        childColumns = ["nickname"],
        onDelete = ForeignKey.NO_ACTION // o un altro comportamento onDelete
    )
]
)*/
data class Constraints(
    @PrimaryKey/*(autoGenerate = true) val id: Long = 0,*/
    val nickname: String,
    val lunedi: Int,
    val martedi: Int,
    val mercoledi: Int,
    val giovedi: Int,
    val venerdi: Int,
    val sabato: Int,
    val domenica: Int,
)
