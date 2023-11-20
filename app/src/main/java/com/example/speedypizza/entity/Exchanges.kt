package com.example.speedypizza.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity=User::class, parentColumns =["nickname"], childColumns = ["sender"]),
    ForeignKey(entity=User::class, parentColumns =["nickname"], childColumns = ["receiver"]),
    ForeignKey(entity=Days::class, parentColumns =["day"], childColumns = ["senderShift"]),
    ForeignKey(entity=Days::class, parentColumns =["day"], childColumns = ["receiverShift"])
]
)
data class Exchanges(
    @PrimaryKey
    val sender: String,
    @PrimaryKey
    val receiver: String,
    @PrimaryKey
    val senderShift:String,
    @PrimaryKey
    val receiverShift: String
)