package com.example.speedypizza

import android.os.Parcelable
import androidx.compose.runtime.mutableStateOf
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

class Profile {


    @Parcelize
    class ProfileClass : Parcelable {

        @IgnoredOnParcel
        val nickname = mutableStateOf("")

        @IgnoredOnParcel
        val email = mutableStateOf("")

        @IgnoredOnParcel
        val role = mutableStateOf("")



    }
}