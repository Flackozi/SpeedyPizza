package com.example.speedypizza.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedypizza.screens.rider.ScrittaIniziale
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.start_color


@Preview
@Composable
fun CreateAccountPage(){


    Box(
        modifier = Modifier
            .background(start_color)
            .fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp))
            ScrittaIniziale(string = "My Rider")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp))
            CreateAccountInfo()
        }
    }

}

@Composable
fun CreateAccountInfo(){



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(boxcol)
            .padding(10.dp)


    ) {

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))

        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
            Text(text = "New Profile",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
            Text(text = "Please enter your basic information and set up new password for secure login.",
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold)
            )
        }



    }
}