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
import com.example.speedypizza.screens.common.CreateAccountPage
import com.example.speedypizza.screens.common.LoginPage
import com.example.speedypizza.screens.common.ProfileScreen
import com.example.speedypizza.screens.common.ShiftsPage
import com.example.speedypizza.screens.rider.ConstraintScreen
import com.example.speedypizza.screens.rider.ExchangeRequests
import com.example.speedypizza.screens.rider.RiderHomeScreen
import com.example.speedypizza.screens.rider.SchermataMessaggi
import com.example.speedypizza.screens.viewmodel.CalendarViewModel
import com.example.speedypizza.screens.viewmodel.ConstraintViewModel
import com.example.speedypizza.screens.viewmodel.DaysViewModel
import com.example.speedypizza.screens.viewmodel.ExchangeViewModel
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.screens.viewmodel.MessageViewModel
import com.example.speedypizza.screens.viewmodel.MyRiderViewModel
import com.example.speedypizza.screens.viewmodel.ShiftsViewModel
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


               val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.LoginViewModelFactory(context.applicationContext as Application))
               val constraintViewModel: ConstraintViewModel= viewModel(factory=ConstraintViewModel.ConstraintViewModelFactory(context.applicationContext as Application))
               val myRiderViewModel: MyRiderViewModel = viewModel(factory = MyRiderViewModel.MyRiderViewModelFactory(context.applicationContext as Application))
               val exchangeViewModel: ExchangeViewModel =viewModel(factory=ExchangeViewModel.ExchangeViewModelFactory(context.applicationContext as Application))
               val createCalendar: CalendarViewModel = viewModel(factory = CalendarViewModel.CalendarViewModelFactory(context.applicationContext as Application))
               val messageViewModel: MessageViewModel = viewModel(factory = MessageViewModel.MessageViewModelFactory(context.applicationContext as Application))
               val shiftsViewModel: ShiftsViewModel = viewModel(factory = ShiftsViewModel.ShiftsViewModelFactory(context.applicationContext as Application))
               val daysViewModel: DaysViewModel = viewModel(factory = DaysViewModel.DaysViewModelFactory(context.applicationContext as Application))

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
                          LoginPage(navController, loginViewModel)
                          user = loginViewModel.loggedUser
                          user?.let { it1 -> Log.i("valuetry44444: ", it1.nickname) }


                       }
                       composable("riderHome") {
                           messageViewModel.retrieveMessages(user!!.nickname)
                           RiderHomeScreen(navController, loginViewModel, messageViewModel) }
                       composable("messagesPage"){
                           SchermataMessaggi(navController, loginViewModel, messageViewModel) }
                       composable("adminHome"){
                           myRiderViewModel.retrieveMyRider()
                           AdminDashboard(navController, loginViewModel) }
                       composable("exchangePage"){
                           exchangeViewModel.retrieveRiderShifts()
                           exchangeViewModel.retrieveMyRider()

                           exchangeViewModel.retrieveExchange(loginViewModel.loggedUser!!.nickname)
                           ExchangeRequests(navController, loginViewModel, exchangeViewModel) }
                       composable("profilePage"){ ProfileScreen(navController,loginViewModel)}
                       composable("shiftPage"){
                           daysViewModel.getDays()
                           shiftsViewModel.getShifts()
                           ShiftsPage(navController, loginViewModel, shiftsViewModel.allShifts, daysViewModel.daysInfo)
                       }
                       composable("constraintsPage"){ ConstraintScreen(navController,loginViewModel, constraintViewModel) }
                       composable("myRiderPage"){
                           //myRiderViewModel.retrieveMyRider()
                           MyRiderScreen(navController,loginViewModel, myRiderViewModel) }
                       composable("createCalendarPage"){
                           //myRiderViewModel.retrieveMyRider()
                           constraintViewModel.getConstraints()
                           CreateCalendar(navController,loginViewModel, myRiderViewModel, createCalendar, constraintViewModel)}
                       composable("newAccountPage") { CreateAccountPage(navController, loginViewModel) }



                   }

               }
            }
        }
    }
}