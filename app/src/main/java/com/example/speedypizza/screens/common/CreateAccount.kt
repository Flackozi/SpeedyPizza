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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedypizza.R
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
            ScrittaIniziale(string = "Create Account")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp))
            CreateAccountInfo()
        }
    }

}

@Composable
fun CreateAccountInfo(){

    var itemList = listOf("Nickname", "Name", "Surname", "Phone", "Email", "Password")
    var isChecked by remember { mutableStateOf(false) }
    val buttonColor = ButtonDefaults.buttonColors(start_color)

    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Black)) {
            append("I have read & agree to the ")
        }
        withStyle(style = SpanStyle(color = start_color)) {
            append("Terms and Conditions ")
        }
        withStyle(style = SpanStyle(color = Color.Black)) {
            append("and ")
        }
        withStyle(style = SpanStyle(color = start_color)) {
            append("Privacy Policy")
        }
    }


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
            .height(15.dp))

        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)) {
            Text(text = "New Profile",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(5.dp))
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)) {
            Text(text = "Please enter your basic information and set up new password for secure login.",
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold)
            )
        }


        itemList.forEach{item ->
            Item(item)
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
        }

        Row(modifier = Modifier.fillMaxWidth().height(50.dp), horizontalArrangement = Arrangement.Center, verticalAlignment =Alignment.CenterVertically) {

            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                modifier = Modifier.padding(16.dp)
            )

            Text(text = text, modifier = Modifier.width(200.dp))

        }
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        Button(
            onClick = { /*creazione account*/ },
            colors = buttonColor,
            modifier = Modifier
                .width(230.dp)
                .height(55.dp)
                .shadow(elevation = 5.dp, shape = CircleShape)
        ) {
            Text(text = "Create Profile", color = Color.White)
        }





    }
}

@Composable
fun Item(item: String){

    val icon: Painter

    val text = remember { mutableStateOf("") }

    if(item == "Nickname" || item == "Name" || item == "Surname"){
        icon = painterResource(id = R.drawable.baseline_person_outline_24)
    }else if(item == "Phone"){
         icon = painterResource(id = R.drawable.baseline_contact_phone_24)
    }else if(item == "Email"){
         icon = painterResource(id = R.drawable.baseline_mail_outline_24)
    }else{
         icon = painterResource(id = R.drawable.baseline_lock_24)
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.Center
        ){

        OutlinedTextField(
            value = text.value,
            onValueChange = { text.value = it},
            label = { Text(text = item)},
            placeholder = { Text(text = item)},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            trailingIcon = {
                Icon(painter = icon, contentDescription = null)
            }
        )
    }
}