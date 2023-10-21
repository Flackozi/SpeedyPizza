package com.example.speedypizza.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedypizza.R
import com.example.speedypizza.ui.theme.RedBackground

//contenitore
@Preview
@Composable
fun HomeScreen(){
    Box(modifier = Modifier
        .background(RedBackground)
        .fillMaxSize()
    ){
        Column {
            BarraSuperiore()
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
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Search",
                tint = Color.Black,
                modifier = Modifier.size(35.dp)
            )
        }
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End

        ){
//            Icon(
//                painter = painterResource(id = R.drawable.menu),
//                contentDescription = "Search",
//                tint = Color.White,
//                modifier = Modifier.size(35.dp)
//            )
        }
    }
}