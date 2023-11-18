package com.example.speedypizza.entity

import androidx.annotation.IntegerRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Types.INTEGER

@Entity(foreignKeys=[ForeignKey(entity=User::class, parentColumns =["nickname"], childColumns = ["nickname"])])
data class Constraints(
    @PrimaryKey
    val nickname: String,
    val max: Int,
    val min: Int,
    val lunedi: Int,
    val martedi: Int,
    val mercoledi: Int,
    val giovedi: Int,
    val venerdi: Int,
    val sabato: Int,
    val domenica: Int
)
