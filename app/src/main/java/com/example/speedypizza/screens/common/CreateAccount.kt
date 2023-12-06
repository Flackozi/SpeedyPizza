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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.speedypizza.R
import com.example.speedypizza.entity.User
import com.example.speedypizza.screens.rider.ScrittaIniziale
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.start_color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun CreateAccountPage(navController: NavHostController, viewModel: LoginViewModel) {


    Box(
        modifier = Modifier
            .background(start_color)
            .fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp))
            ScrittaIniziale(string = stringResource(R.string.Create_Account))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp))
            CreateAccountInfo(viewModel, navController)
        }
    }

}

@Composable
fun CreateAccountInfo(viewModel: LoginViewModel, navController: NavHostController){

    val itemList = listOf("Nickname", stringResource(R.string.Name), stringResource(R.string.Surname), stringResource(R.string.Phone2), "Email", "Password")
    var isChecked by remember { mutableStateOf(false) }
    val buttonColor = ButtonDefaults.buttonColors(start_color)
    val profileData: MutableList<String> = mutableListOf("", "", "", "", "", "")


    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Black)) {
            append(stringResource(R.string.Ihave))
        }
        withStyle(style = SpanStyle(color = start_color)) {
            append(stringResource(R.string.Terms))
        }
        withStyle(style = SpanStyle(color = Color.Black)) {
            append(stringResource(R.string.and))
        }
        withStyle(style = SpanStyle(color = start_color)) {
            append(stringResource(R.string.Privacy))
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
            Text(text = stringResource(R.string.NewProfile),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(5.dp))
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)) {
            Text(text = stringResource(R.string.Please),
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold)
            )
        }


        itemList.forEach{item ->
            Item(item, profileData)
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp), horizontalArrangement = Arrangement.Center, verticalAlignment =Alignment.CenterVertically) {

            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                modifier = Modifier.padding(16.dp)
            )

            Text(text = text, modifier = Modifier.width(220.dp))

        }
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        Button(
            onClick = {

                CoroutineScope(Dispatchers.Main).launch {

                    viewModel.createAccount(User(profileData[0], profileData[1], profileData[2], profileData[5], profileData[3], profileData[4], 1))
                    navController.navigate("riderHome")
                }


            },
            colors = buttonColor,
            modifier = Modifier
                .width(230.dp)
                .height(55.dp)
                .shadow(elevation = 5.dp, shape = CircleShape)
        ) {
            Text(text = stringResource(R.string.CreateProfile), color = Color.White)
        }





    }
}

@Composable
fun Item(item: String, profileData: MutableList<String>){

    val icon: Painter

    val text = remember { mutableStateOf("") }

    if(item == "Nickname"){
        icon = painterResource(id = R.drawable.baseline_person_outline_24)
        profileData.add(0, text.value)
    }else if(item == "Name"){
        icon = painterResource(id = R.drawable.baseline_person_outline_24)
        profileData.add(1, text.value)
    }else if(item == "Surname") {
        icon = painterResource(id = R.drawable.baseline_person_outline_24)
        profileData.add(2, text.value)
    }else if(item == "Phone"){
        icon = painterResource(id = R.drawable.baseline_contact_phone_24)
        profileData.add(3, text.value)
    }else if(item == "Email"){
        icon = painterResource(id = R.drawable.baseline_mail_outline_24)
        profileData.add(4, text.value)
    }else{
        icon = painterResource(id = R.drawable.baseline_lock_24)
        profileData.add(5, text.value)
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
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
            },
            textStyle = TextStyle(fontSize = 12.sp)
        )
    }
}