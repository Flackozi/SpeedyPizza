package com.example.speedypizza.entity

import android.os.Parcelable
import androidx.compose.runtime.mutableStateOf
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class User: Parcelable {

    @IgnoredOnParcel
    val nickname = mutableStateOf("")

    @IgnoredOnParcel
    val password = mutableStateOf(100)

    @IgnoredOnParcel
    val phoneNumber = mutableStateOf(true)

    @IgnoredOnParcel
    val email = mutableStateOf(true)

}