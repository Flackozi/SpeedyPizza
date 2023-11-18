package com.example.speedypizza.screens.rider

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.speedypizza.R
import com.example.speedypizza.entity.Messaggio
import com.example.speedypizza.screens.common.MessageItem
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//contenitore



@Composable
fun RiderHomeScreen(navController: NavHostController, viewModel: LoginViewModel) {
    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color ),
        startY = 0f,
        endY = 2000f
    )
    //il constraint qui in realta' e' inutile
    ConstraintLayout {
        val (box)=createRefs()
        Box(modifier = Modifier
            .background(brush = gradient)
            .fillMaxSize()
            .constrainAs(box) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ){
            Column {
                BarraSuperiore(navController, viewModel)
                ScrittaIniziale("SpeedyPizza")
                Spacer(modifier = Modifier.height(90.dp))
                Bottoni(navController)
            }
        }

    }

}

//scritta iniziale con immagine pizza
@Composable
fun ScrittaIniziale(string: String){



        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally)

        {
            Box(modifier = Modifier.offset(y=0.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.pizzaslice),
                    contentDescription = stringResource(id = R.string.image_content_description),
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(71.dp)
                        .rotate(300f)
                        .align(Alignment.Center)
                )



                Text(
                    text = string,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.mogra)),
                        fontSize = 35.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.offset(y=50.dp)
                )
            }

        }







}

//Barra superiore con bottone del profilo e tre lineette
@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun BarraSuperiore(navController: NavHostController, viewModel: LoginViewModel) {


    var expanded by remember{mutableStateOf(false)}


   TopAppBar(title = {
       Text(text = "")
   },
       colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent ),
       navigationIcon = {


           Row (modifier = Modifier.fillMaxSize(),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.SpaceBetween
           ){





               Icon(
                   painter = painterResource(id = R.drawable.baseline_account_box_24),
                   contentDescription = "Profile",
                   modifier = Modifier
                       .clickable{
                           CoroutineScope(Dispatchers.Main).launch {
                               navController.navigate("ProfilePage")
                           }
                       }
                       .size(40.dp),
                   tint = Color.White,


               )

                Box{
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            painter = painterResource(id =R.drawable.ic_menu),
                            contentDescription = "Menu",
                            tint = Color.White,
                            modifier = Modifier.size(45.dp),

                            )
                    }

                    DropdownMenu(expanded = expanded, onDismissRequest = {
                        expanded = false
                    },
                        modifier = Modifier
                            .background(
                                Color.White
                            )
                            .align(Alignment.CenterEnd)
                    ) {

                        DropdownMenuItem(
                            text = { Text("Home") },
                            onClick = {

                                if(viewModel.loggedUser!!.role==1) navController.navigate("riderHome")
                                else navController.navigate("adminHome")

                                      },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Home,
                                    contentDescription = null
                                )
                            })
                        DropdownMenuItem(
                            text = { Text("Settings") },
                            onClick = { /* Handle settings! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Settings,
                                    contentDescription = null
                                )
                            })
                        Divider()
                        DropdownMenuItem(
                            text = { Text("Send Feedback") },
                            onClick = { /* Handle send feedback! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Email,
                                    contentDescription = null
                                )
                            },
                            )
                    }
                }

           }


   }

       )



}

//@Preview
@Composable
fun Bottoni(navController: NavHostController) {

    var elencoMessaggi = listOf(
        Messaggio(1, "Nuovi turni disponibili", "21/10/2023", "Cambio Turni"),
        Messaggio(2, "Calendario dei turni pubblicato", "19/10/2023", "Nuovo Calendario"),
        Messaggio(3, "Nuovi turni disponibili", "21/10/2023", "Cambio Turni"),
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 30.dp))
            .background(boxcol)
            .padding(10.dp)

    ){
        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(10.dp)
                //.shadow(elevation=3.dp)
                //.border(BorderStroke(width = 3.dp, color = Color.LightGray))
                .background(Color.Transparent)
        ){
//                Spacer(modifier=Modifier.height(3.dp))
            LazyColumn(
//                    verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier=Modifier
                    .padding(10.dp)
//                        .offset(y=4.dp)
            ){
                items(elencoMessaggi) { message ->
                    MessageItem(message)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
//                    .offset(y = 180.dp), // Questo fa sì che il Box occupi tutto lo spazio disponibile

        ){
            Button(
                onClick={
                    navController.navigate("shiftPage")
                    println("This is myTurn")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor=Color.White,
                    contentColor=Color.Black
                ),
                modifier = Modifier
                    .width(130.dp)
                    .height(130.dp)
                    .offset(x = 40.dp, y = 40.dp)
                    .shadow(20.dp),
                shape = RoundedCornerShape(topStart=15.dp, topEnd=15.dp, bottomEnd = 15.dp, bottomStart=15.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(2.dp)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar_foreground),
                        contentDescription = "Calendar",
                        modifier = Modifier
                            .size(60.dp)
                    )
                    Text(stringResource(R.string.Shifts), modifier = Modifier.offset(y=5.dp))
                }

            }

            Button(
                onClick = {
                    navController.navigate("exchangePage")
                    println("Exchange requests")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(130.dp)
                    .height(130.dp)
                    .offset(x = 200.dp, y = 40.dp)
                    .shadow(20.dp),
                shape = RoundedCornerShape(topStart=15.dp, topEnd=15.dp, bottomEnd = 15.dp, bottomStart=15.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(2.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_change),
                        contentDescription = "Change",
                        tint = Color.Black,
                        modifier = Modifier.size(60.dp)
                    )
                    Text(stringResource(R.string.Requests))
                }

            }

            Button(
                onClick = {
                    navController.navigate("messagesPage")
                    println("This is Messages")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(130.dp)
                    .height(130.dp)
                    .offset(x = 40.dp, y = 200.dp)
                    .shadow(20.dp),
                shape = RoundedCornerShape(topStart=15.dp, topEnd=15.dp, bottomEnd = 15.dp, bottomStart=15.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(2.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "Notification",
                        tint = Color.Black,
                        modifier = Modifier.size(60.dp)
                    )
                    Text(stringResource(id = R.string.Messages))
                }

            }


            Button(
                onClick = {
                    navController.navigate("constraintsPage")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(130.dp)
                    .height(130.dp)
                    .offset(x = 200.dp, y = 200.dp)
                    .shadow(20.dp),
                shape = RoundedCornerShape(topStart=15.dp, topEnd=15.dp, bottomEnd = 15.dp, bottomStart=15.dp)


            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(1.dp)
                ) {
                    Icon(
                        painter = painterResource(id =R.drawable.ic_constraints),
                        contentDescription = "Change",
                        tint = Color.Black,
                        modifier = Modifier.size(60.dp)
                    )
                    Text(text= stringResource(id = R.string.Constraints))
                }

            }
        }
    }


}
/*
@Preview
@Composable
fun Preview() {
    RiderHomeScreen(rememberNavController(), user)
}*/