package com.example.speedypizza.screens.admin

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.speedypizza.screens.rider.BarraSuperiore
import com.example.speedypizza.screens.rider.CheckBox
import com.example.speedypizza.screens.rider.ScrittaIniziale
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.start_color


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CreateCalendar(navController: NavHostController) {


    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var expanded3 by remember { mutableStateOf(false) }
    var expanded4 by remember { mutableStateOf(false) }
    var expanded5 by remember { mutableStateOf(false) }
    var expanded6 by remember { mutableStateOf(false) }
    var expanded7 by remember { mutableStateOf(false) }



    val items = listOf(
        "Matteo",
        "Carlo",
        "Flacko",
        "Amrando",
        "Francesco",
        "Franco"
    )

    val checkboxStates1=remember{ mutableStateMapOf<String, Boolean>() }
    items.forEach { item->
        if(!checkboxStates1.contains(item)){
            checkboxStates1[item]=false
        }
    }
    val checkboxStates2=remember{ mutableStateMapOf<String, Boolean>() }
    items.forEach { item->
        if(!checkboxStates2.contains(item)){
            checkboxStates2[item]=false
        }
    }
    val checkboxStates3=remember{ mutableStateMapOf<String, Boolean>() }
    items.forEach { item->
        if(!checkboxStates3.contains(item)){
            checkboxStates3[item]=false
        }
    }
    val checkboxStates4=remember{ mutableStateMapOf<String, Boolean>() }
    items.forEach { item->
        if(!checkboxStates4.contains(item)){
            checkboxStates4[item]=false
        }
    }
    val checkboxStates5=remember{ mutableStateMapOf<String, Boolean>() }
    items.forEach { item->
        if(!checkboxStates5.contains(item)){
            checkboxStates5[item]=false
        }
    }
    val checkboxStates6=remember{ mutableStateMapOf<String, Boolean>() }
    items.forEach { item->
        if(!checkboxStates6.contains(item)){
            checkboxStates6[item]=false
        }
    }
    val checkboxStates7=remember{ mutableStateMapOf<String, Boolean>() }
    items.forEach { item->
        if(!checkboxStates7.contains(item)){
            checkboxStates7[item]=false
        }
    }

    var selectedItem by remember { mutableStateOf(items[0]) }

    Box(
        modifier = Modifier
            .background(start_color)
            .fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            BarraSuperiore(navController)
            ScrittaIniziale(string = "New Calendar")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp))

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
                    .height(60.dp))

                Box(modifier = Modifier.width(300.dp)){

                    Button(
                        onClick = { expanded1 = !expanded1 },
                        modifier = Modifier.width(300.dp),
                        colors = ButtonDefaults.buttonColors(start_color)
                    ) {
                        Text(text = "Lunedi")
                    }


                    DropdownMenu(
                        expanded = expanded1,
                        onDismissRequest = { expanded1 = false },
                        modifier = Modifier
                            .width(300.dp)
                            .background(boxcol)
                    ) {

                            items.forEach { item ->

                                DropdownMenuItem(
                                    text = {
                                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                            Text(text = item)
                                            Spacer(modifier = Modifier.width(10.dp))
                                            CheckBox(checked = checkboxStates1[item] ?:false, onCheckedChange = { isChecked ->checkboxStates1[item]=isChecked }, 1)
                                        }


                                           },
                                        onClick = {
                                            selectedItem = item
                                            expanded1 = false
                                        }
                                    )



                            }

                    }


                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))

                Box(modifier = Modifier.width(300.dp)){

                    Button(
                        onClick = { expanded2 = !expanded2 },
                        modifier = Modifier.width(300.dp),
                        colors = ButtonDefaults.buttonColors(start_color)
                    ) {
                        Text(text = "Martedi")
                    }


                    DropdownMenu(
                        expanded = expanded2,
                        onDismissRequest = { expanded2 = false },
                        modifier = Modifier
                            .width(300.dp)
                            .background(boxcol)
                    ) {
                        items.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(text = item)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    CheckBox(checked = checkboxStates2[item] ?:false, onCheckedChange = { isChecked ->checkboxStates2[item]=isChecked }, 1)
                                }},
                                onClick = {
                                    selectedItem = item
                                    expanded2 = false
                                })


                        }
                    }

                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))

                Box(modifier = Modifier.width(300.dp)){

                    Button(
                        onClick = { expanded3 = !expanded3 },
                        modifier = Modifier.width(300.dp),
                        colors = ButtonDefaults.buttonColors(start_color)
                    ) {
                        Text(text = "Mercoledi")
                    }


                    DropdownMenu(
                        expanded = expanded3,
                        onDismissRequest = { expanded3 = false },
                        modifier = Modifier
                            .width(300.dp)
                            .background(boxcol)
                    ) {
                        items.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(text = item)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    CheckBox(checked = checkboxStates3[item] ?:false, onCheckedChange = { isChecked ->checkboxStates3[item]=isChecked }, 1)
                                }},
                                onClick = {
                                    selectedItem = item
                                    expanded3 = false
                                })


                        }
                    }

                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))

                Box(modifier = Modifier.width(300.dp)){

                    Button(
                        onClick = { expanded4 = !expanded4 },
                        modifier = Modifier.width(300.dp),
                        colors = ButtonDefaults.buttonColors(start_color)
                    ) {
                        Text(text = "Giovedi")
                    }


                    DropdownMenu(
                        expanded = expanded4,
                        onDismissRequest = { expanded4 = false },
                        modifier = Modifier
                            .width(300.dp)
                            .background(boxcol)
                    ) {
                        items.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(text = item)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        CheckBox(checked = checkboxStates4[item] ?:false, onCheckedChange = { isChecked ->checkboxStates4[item]=isChecked }, 1)
                                    }
                                },
                                onClick = {
                                    selectedItem = item
                                    expanded4 = false
                                })


                        }
                    }

                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))

                Box(modifier = Modifier.width(300.dp)){

                    Button(
                        onClick = { expanded5 = !expanded5 },
                        modifier = Modifier.width(300.dp),
                        colors = ButtonDefaults.buttonColors(start_color)
                    ) {
                        Text(text = "Venerdi")
                    }


                    DropdownMenu(
                        expanded = expanded5,
                        onDismissRequest = { expanded5 = false },
                        modifier = Modifier
                            .width(300.dp)
                            .background(boxcol)
                    ) {
                        items.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(text = item)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        CheckBox(checked = checkboxStates5[item] ?:false, onCheckedChange = { isChecked ->checkboxStates5[item]=isChecked }, 1)
                                    }
                                },
                                onClick = {
                                    selectedItem = item
                                    expanded5 = false
                                })


                        }
                    }

                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))

                Box(modifier = Modifier.width(300.dp)){

                    Button(
                        onClick = { expanded6 = !expanded6 },
                        modifier = Modifier.width(300.dp),
                        colors = ButtonDefaults.buttonColors(start_color)
                    ) {
                        Text(text = "Sabato")
                    }


                    DropdownMenu(
                        expanded = expanded6,
                        onDismissRequest = { expanded6 = false },
                        modifier = Modifier
                            .width(300.dp)
                            .background(boxcol)
                    ) {
                        items.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(text = item)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        CheckBox(checked = checkboxStates6[item] ?:false, onCheckedChange = { isChecked ->checkboxStates6[item]=isChecked }, 1)
                                    }
                                },
                                onClick = {
                                    selectedItem = item
                                    expanded6 = false
                                })


                        }
                    }

                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))

                Box(modifier = Modifier.width(300.dp)){

                    Button(
                        onClick = { expanded7 = !expanded7 },
                        modifier = Modifier.width(300.dp),
                        colors = ButtonDefaults.buttonColors(start_color)
                    ) {
                        Text(text = "Domenica")
                    }


                    DropdownMenu(
                        expanded = expanded7,
                        onDismissRequest = { expanded7 = false },
                        modifier = Modifier
                            .width(300.dp)
                            .background(boxcol)
                    ) {
                        items.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(text = item)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        CheckBox(checked = checkboxStates7[item] ?:false, onCheckedChange = { isChecked ->checkboxStates7[item]=isChecked }, 1)
                                    }
                                },
                                onClick = {
                                    selectedItem = item
                                    expanded7 = false
                                })


                        }
                    }

                }


                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp))

                Row(horizontalArrangement = Arrangement.Center) {
                    Button(

                        colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(topStart=15.dp, topEnd=15.dp, bottomEnd = 15.dp, bottomStart=15.dp),
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp)
                            .shadow(20.dp),

                    ) {
                        Text(
                            text = "Submit",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }

            }

        }


    }
}
