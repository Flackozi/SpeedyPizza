package com.example.speedypizza.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity=User::class, parentColumns =["nickname"], childColumns = ["sender"]),
    ForeignKey(entity=User::class, parentColumns =["nickname"], childColumns = ["receiver"]),
    ForeignKey(entity=Days::class, parentColumns =["day"], childColumns = ["senderShift"]),
    ForeignKey(entity=Days::class, parentColumns =["day"], childColumns = ["receiverShift"])
],
    primaryKeys = ["sender", "receiver", "senderShift", "receiverShift"]
)
data class Exchanges(
    val sender: String,
    val receiver: String,
    val senderShift:String,
    val receiverShift: String
)