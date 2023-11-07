package com.example.speedypizza

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.speedypizza.screens.admin.AdminDashboard
import com.example.speedypizza.screens.admin.MyRiderScreen
import com.example.speedypizza.screens.common.LoginPage
import com.example.speedypizza.screens.common.ProfileScreen
import com.example.speedypizza.screens.rider.ConstraintScreen
import com.example.speedypizza.screens.rider.ExchangeRequests
import com.example.speedypizza.screens.rider.RiderHomeScreen
import com.example.speedypizza.screens.rider.SchermataMessaggi
import com.example.speedypizza.screens.rider.ShiftsPage
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.ui.theme.SpeedyPizzaTheme
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color

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
    //ExchangeRequests()
    val context = LocalContext.current

    val viewModel: LoginViewModel = viewModel(factory = LoginViewModel.LoginViewModelFactory(context.applicationContext as Application))

    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color ),
        startY = 0f,
        endY = 2000f
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ){
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "loginPage"){
            composable("loginPage") {

                LoginPage(navController, viewModel)
            }
            composable("riderHome") { RiderHomeScreen(navController) }
            composable("messagesPage"){ SchermataMessaggi(navController)}
            composable("adminHome"){ AdminDashboard(navController) }
            composable("exchangePage"){ ExchangeRequests(navController) }
            composable("profilePage"){ ProfileScreen(navController)}
            composable("shiftPage"){ ShiftsPage(navController) }
            composable("constraintsPage"){ ConstraintScreen(navController) }
            composable("myRiderPage"){ MyRiderScreen(navController) }
        }

    }
}