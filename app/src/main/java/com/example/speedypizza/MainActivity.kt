package com.example.speedypizza

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.speedypizza.entity.User
import com.example.speedypizza.screens.admin.AdminDashboard
import com.example.speedypizza.screens.admin.CreateCalendar
import com.example.speedypizza.screens.admin.MyRiderScreen
import com.example.speedypizza.screens.common.LoginPage
import com.example.speedypizza.screens.common.ProfileScreen
import com.example.speedypizza.screens.rider.ConstraintScreen
import com.example.speedypizza.screens.rider.ExchangeRequests
import com.example.speedypizza.screens.rider.RiderHomeScreen
import com.example.speedypizza.screens.common.SchermataMessaggi
import com.example.speedypizza.screens.common.ShiftsPage
import com.example.speedypizza.screens.viewmodel.ConstraintViewModel
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.ui.theme.SpeedyPizzaTheme
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color
@RequiresApi(Build.VERSION_CODES.Q)
class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferencesProfile: SharedPreferences
    private lateinit var userViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        sharedPreferencesProfile = getSharedPreferences("Profile", Context.MODE_PRIVATE)


        setContent {

           SpeedyPizzaTheme {

              // val profile = remember { Profile.ProfileClass() }

               val context = LocalContext.current

               var user: User? = null
               /*var user = User("","","","","","",0)



               profile.nickname = sharedPreferencesProfile.*/

               val viewModel: LoginViewModel = viewModel(factory = LoginViewModel.LoginViewModelFactory(context.applicationContext as Application))
               val constraintViewModel: ConstraintViewModel= viewModel(factory=ConstraintViewModel.ConstraintViewModelFactory(context.applicationContext as Application))

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

                           //LoginPage(navController, viewModel)
                          LoginPage(navController, viewModel)
                          user = viewModel.loggedUser
                          user?.let { it1 -> Log.i("valuetry44444: ", it1.nickname) }


                       }
                       composable("riderHome") { RiderHomeScreen(navController, viewModel) }
                       composable("messagesPage"){ SchermataMessaggi(navController, viewModel) }
                       composable("adminHome"){ AdminDashboard(navController, viewModel) }
                       composable("exchangePage"){ ExchangeRequests(navController, viewModel) }
                       composable("profilePage"){ ProfileScreen(navController,viewModel)}
                       composable("shiftPage"){ ShiftsPage(navController, viewModel) }
                       composable("constraintsPage"){ ConstraintScreen(navController,viewModel, constraintViewModel) }
                       composable("myRiderPage"){ MyRiderScreen(navController,viewModel) }
                       composable("CreateCalendarPage"){ CreateCalendar(navController,viewModel)}


                   }

               }
            }
        }
    }
}

/*@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SpeedyPizzaApp(sharedPreferencesProfile: SharedPreferences) {

    val context = LocalContext.current
    var user = User("","","","","","",0)



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

                //LoginPage(navController, viewModel)
                user = LoginPage(navController, viewModel)
            }
            composable("riderHome") { RiderHomeScreen(navController, user) }
            composable("messagesPage"){ SchermataMessaggi(navController, user) }
            composable("adminHome"){ AdminDashboard(navController, user) }
            composable("exchangePage"){ ExchangeRequests(navController, user) }
            composable("profilePage"){ ProfileScreen(navController, user)}
            composable("shiftPage"){ ShiftsPage(navController, user) }
            composable("constraintsPage"){ ConstraintScreen(navController, user) }
            composable("myRiderPage"){ MyRiderScreen(navController, user) }
            composable("CreateCalendarPage"){ CreateCalendar(navController, user)}


        }

    }
}*/