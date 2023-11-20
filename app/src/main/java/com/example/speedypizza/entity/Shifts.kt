package com.example.speedypizza.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(foreignKeys=[
    ForeignKey(entity=User::class, parentColumns =["nickname"], childColumns = ["rider"]),
    ForeignKey(entity=Days::class, parentColumns =["day"], childColumns = ["day"])
],
    primaryKeys = ["rider","day"]
    )
data class Shifts (
    val rider: String,
    val day: String
)

