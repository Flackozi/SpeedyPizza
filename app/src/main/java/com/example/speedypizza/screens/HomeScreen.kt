package com.example.speedypizza.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedypizza.R
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color

//contenitore
@Preview
@Composable
fun HomeScreen(){
    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color ),
        startY = 0f,
        endY = 2000f
    )
    Box(modifier = Modifier
        .background(brush = gradient)
        .fillMaxSize()
    ){
        Column {
            BarraSuperiore()
            ScrittaIniziale()
            MyTurn()
            BottoniCentrali2()
            WorkConstraints()
        }
    }
}

//scritta iniziale con immagine pizza
@Preview
@Composable
fun ScrittaIniziale(){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(2.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pizza),
                    contentDescription = stringResource(id = R.string.image_content_description),
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 5.dp)
                )
                Text(text = "SpeedyPizza",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.mogra)),
                        fontSize = 35.sp,
                        color = Color.LightGray)
                )
            }
        }

    }
}

//Barra superiore con bottone del profilo e tre lineette
@Preview
@Composable
fun BarraSuperiore (){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
            
        ){
            Icon(
                painter = painterResource(id =R.drawable.ic_account),
                contentDescription = "Profile",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
        Icon(
            painter = painterResource(id =R.drawable.ic_menu),
            contentDescription = "Menu",
            tint = Color.Black,
            modifier = Modifier.size(45.dp)
        )
    }
}

@Preview
@Composable
fun MyTurn(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Box(
            modifier = Modifier.fillMaxWidth(), // Questo fa sì che il Box occupi tutto lo spazio disponibile
            contentAlignment = Alignment.Center // Centra il contenuto all'interno del Box
        ){
            Button(
                onClick={
                    //qui ci va il metodo associato al bottone
                    println("This is myTurn")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor=Color.LightGray,
                    contentColor=Color.Black
                ),
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp),
                shape = RoundedCornerShape(30.dp)

           ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(2.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_calendar_foreground),
                        contentDescription = stringResource(id =R.string.image_content_description),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 5.dp)
                    )
                    Text(stringResource(id = R.string.My_Turns))
                }

            }
        }
    }
}

@Preview
@Composable
fun BottoniCentrali2(){

    Box (
        modifier=Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Row (
            horizontalArrangement = Arrangement.Center, //distribuisce lo spazio tra i bottoni
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)

        ) {
            /*bottone a sinistra*/
            Button(
                onClick = {
                    //qui ci va il metodo associato al botone
                    println("Exchange requests")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp),
                shape = RoundedCornerShape(30.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(2.dp)
                ) {
                    Icon(
                        painter = painterResource(id =R.drawable.ic_change),
                        contentDescription = "Change",
                        tint = Color.Black,
                        modifier = Modifier.size(35.dp)
                    )
                    Text(stringResource(R.string.Requests))
                }

            }
            Spacer(modifier=Modifier.width(30.dp))
            /*bottone a destra*/
            Button(
                onClick = {
                    //qui ci va il metodo associato al botone
                    println("This is Messages")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp),
                shape = RoundedCornerShape(30.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(2.dp)
                ) {
                    Icon(
                        painter = painterResource(id =R.drawable.ic_notification),
                        contentDescription = "Notification",
                        tint = Color.Black,
                        modifier = Modifier.size(35.dp)
                    )
                    Text(stringResource(id = R.string.Messages))
                }

            }
        }
    }



}

@Preview
@Composable
fun WorkConstraints() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(), // Questo fa sì che il Box occupi tutto lo spazio disponibile
            contentAlignment = Alignment.Center // Centra il contenuto all'interno del Box
        ) {
            Button(
                onClick = {
                    //qui ci va il metodo associato al botone
                    println("This is myTurn")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp),
                shape = RoundedCornerShape(30.dp)

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
                        modifier = Modifier.size(35.dp)
                    )
                    Text(text= stringResource(id = R.string.Constraints))
                }

            }
        }
    }
}