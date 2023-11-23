package com.example.speedypizza.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Message(
    @PrimaryKey
    val messageID: Int,
    val messageText: String,
    val messageDate: String,
    val messageReceiver: String,
    val messageType: Int
)
