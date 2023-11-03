package com.example.speedypizza.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.speedypizza.R
import com.example.speedypizza.screens.rider.BarraSuperiore
import com.example.speedypizza.screens.rider.ScrittaIniziale
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color

/*@Preview
@Composable
fun AdminDashboard() {

    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color),
        startY = 0f,
        endY = 2000f
    )

    ConstraintLayout {
        val (box) = createRefs()
        Box(modifier = Modifier
            .background(brush = gradient)
            .fillMaxSize()
            .constrainAs(box) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Column {
                BarraSuperiore()
                Titolo("Admin Dashboard")
                PrimoMenu()


            }
        }

    }
}


@Composable
fun PrimoMenu() {
    Box(modifier = Modifier.fillMaxWidth().height(150.dp).padding(horizontal = 16.dp, vertical = 32.dp).shadow(elevation = 5.dp)){
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){

            Card(modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
                .clickable {

                },
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)){
                Text(text = "Warning non rispettati")
            }

            Card(modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
                .clickable {

                },
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)){
                Text(text = "Visualizza Calendario")
            }

        }
    }
}

@Composable
    fun Titolo(string: String){

        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

            Box(modifier = Modifier.offset(y = 0.dp)) {
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
                    modifier = Modifier.offset(y = 52.dp)
                )
            }

        }

    }

*/



@Composable
fun AdminDashboard(navController: NavHostController) {
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
                BarraSuperiore()
                ScrittaIniziale("SpeedyPizza")
                Spacer(modifier = Modifier.height(150.dp))
                PrimoMenu()
            }
        }

    }

}

/*
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







}*/

//Barra superiore con bottone del profilo e tre lineette
/*@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable

fun BarraSuperiore (){


    var expanded by remember{ mutableStateOf(false) }


    TopAppBar(title = {
        Text(text = "")
    },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent ),
        navigationIcon = {


            Row (modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){





                Icon(
                    painter = painterResource(id = R.drawable.baseline_account_box_24),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(40.dp),
                    tint = Color.White

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
                            onClick = { /* Handle edit! */ },
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



}*/


@Composable
fun DashBoard(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(boxcol)
            .padding(10.dp)


    ){


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .offset(y = 180.dp), // Questo fa sì che il Box occupi tutto lo spazio disponibile

        ){
            Button(
                onClick={
                    //qui ci va il metodo associato al bottone
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
                    Text(stringResource(R.string.Rider_Management), modifier = Modifier.offset(y=5.dp))
                }

            }

            Button(
                onClick = {
                    //qui ci va il metodo associato al botone
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
                    Text(stringResource(R.string.Create_Calendar))
                }

            }

            Button(
                onClick = {
                    //qui ci va il metodo associato al botone
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
                    //qui ci va il metodo associato al botone
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
@Composable
fun PrimoMenu(){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(boxcol)
            .padding(10.dp)


    ){


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .offset(y = 180.dp), // Questo fa sì che il Box occupi tutto lo spazio disponibile

        ){
            Button(
                onClick={
                    //qui ci va il metodo associato al bottone
                    println("Rider Management")
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
                   // modifier = Modifier.padding(2.dp)
                ){
                    Icon(
                        painter = painterResource(id =R.drawable.baseline_sports_motorsports_24),
                        contentDescription = "Calendar",
                        modifier = Modifier
                            .size(60.dp)
                    )
                    Text(stringResource(R.string.Rider_Management), modifier = Modifier.offset(y=5.dp), textAlign = TextAlign.Center)
                }

            }

            Button(
                onClick = {
                    //qui ci va il metodo associato al bottone
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
                        painter = painterResource(id = R.drawable.baseline_create_24),
                        contentDescription = "Change",
                        tint = Color.Black,
                        modifier = Modifier.size(60.dp)
                    )
                    Text(stringResource(R.string.Create_Calendar), modifier = Modifier.offset(y=5.dp), textAlign = TextAlign.Center)
                }

            }

            Button(
                onClick = {
                    //qui ci va il metodo associato al botone
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
                    Text(stringResource(id = R.string.Messages), textAlign = TextAlign.Center)
                }

            }


            Button(
                onClick = {
                    //qui ci va il metodo associato al botone
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
                        painter = painterResource(id =R.drawable.ic_calendar_foreground),
                        contentDescription = "Change",
                        tint = Color.Black,
                        modifier = Modifier.size(60.dp)
                    )
                    Text(text= stringResource(id = R.string.View_Calendar), textAlign = TextAlign.Center)
                }

            }
        }
    }

}
