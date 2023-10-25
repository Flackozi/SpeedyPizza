package com.example.speedypizza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.speedypizza.screens.ConstraintScreen
import com.example.speedypizza.screens.HomeScreen
import com.example.speedypizza.screens.LoginPage
import com.example.speedypizza.screens.schermataMessaggi
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
    ConstraintScreen()
    //schermataMessaggi()
}