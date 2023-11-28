package com.example.speedypizza.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys=[
    ForeignKey(entity=User::class, parentColumns =["nickname"], childColumns = ["rider"]),
    ForeignKey(entity=Days::class, parentColumns =["day"], childColumns = ["day"])
],
    primaryKeys = ["rider","day"]
)
data class Shifts (
    @PrimaryKey
    val rider: String,
    @PrimaryKey
    val day: String
)

