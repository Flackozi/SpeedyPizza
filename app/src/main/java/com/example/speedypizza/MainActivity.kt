package com.example.speedypizza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.speedypizza.screens.admin.MyRiderScreen
import com.example.speedypizza.ui.theme.SpeedyPizzaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpeedyPizzaTheme {
                SpeedyPizzaApp()
            }
        }
    }
}

@Composable
fun SpeedyPizzaApp(){
//    LoginPage()
    //HomeScreen()
//    ConstraintScreen()
    //schermataMessaggi()
    //ShiftsPage()
    //ProfileScreen()
    MyRiderScreen()
}