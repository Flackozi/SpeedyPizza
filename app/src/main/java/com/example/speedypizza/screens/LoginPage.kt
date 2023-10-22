package com.example.speedypizza.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.example.speedypizza.ui.theme.witheBackground
@Composable
fun LoginPage(){


    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color ),
        startY = 0f,
        endY = 2000f
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
         Box(modifier = Modifier
             .fillMaxSize()
             .background(brush = gradient), contentAlignment = Alignment.TopCenter){

             Image(painterResource(R.drawable.pizza),"content description", modifier = Modifier.offset(y = 100.dp))
         }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SpeedyPizza",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.mogra)), // Sostituisci con il tuo tipo di carattere
                    fontSize = 35.sp,
                    color = Color.White // Sostituisci con il tuo colore
                ),
                modifier = Modifier.padding(16.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.60f).clip(RoundedCornerShape(topStart = 30.dp, topEnd= 30.dp))
                .background(witheBackground)
                .padding(10.dp)
            ) {


        }
    }
}

@Preview
@Composable
fun GradientBackgroundPreview() {
    LoginPage()
}