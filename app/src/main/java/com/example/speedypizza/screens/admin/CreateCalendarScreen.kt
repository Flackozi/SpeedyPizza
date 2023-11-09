package com.example.speedypizza.screens.admin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedypizza.screens.rider.BarraSuperiore
import com.example.speedypizza.screens.rider.CheckBox
import com.example.speedypizza.screens.rider.ScrittaIniziale
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.start_color


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
@Preview
fun DropDownItemDay() {

    var expanded by remember { mutableStateOf(false) }
    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var expanded3 by remember { mutableStateOf(false) }
    var expanded4 by remember { mutableStateOf(false) }
    var expanded5 by remember { mutableStateOf(false) }
    var expanded6 by remember { mutableStateOf(false) }
    var expanded7 by remember { mutableStateOf(false) }

    var exp = mutableListOf(expanded1,expanded2, expanded3, expanded4, expanded5, expanded6, expanded7)

    var text = remember { mutableStateOf("") }

    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")


    val items = listOf(
        "Matteo",
        "Carlo",
        "Flacko",
        "Amrando",
        "Francesco",
        "Franco"
    )

    val checkboxStates1 = remember { mutableStateMapOf<String, Boolean>() }
    items.forEach { item ->
        if (!checkboxStates1.contains(item)) {
            checkboxStates1[item] = false
        }
    }
    val checkboxStates2 = remember { mutableStateMapOf<String, Boolean>() }
    items.forEach { item ->
        if (!checkboxStates2.contains(item)) {
            checkboxStates2[item] = false
        }
    }
    val checkboxStates3 = remember { mutableStateMapOf<String, Boolean>() }
    items.forEach { item ->
        if (!checkboxStates3.contains(item)) {
            checkboxStates3[item] = false
        }
    }
    val checkboxStates4 = remember { mutableStateMapOf<String, Boolean>() }
    items.forEach { item ->
        if (!checkboxStates4.contains(item)) {
            checkboxStates4[item] = false
        }
    }
    val checkboxStates5 = remember { mutableStateMapOf<String, Boolean>() }
    items.forEach { item ->
        if (!checkboxStates5.contains(item)) {
            checkboxStates5[item] = false
        }
    }
    val checkboxStates6 = remember { mutableStateMapOf<String, Boolean>() }
    items.forEach { item ->
        if (!checkboxStates6.contains(item)) {
            checkboxStates6[item] = false
        }
    }
    val checkboxStates7 = remember { mutableStateMapOf<String, Boolean>() }
    items.forEach { item ->
        if (!checkboxStates7.contains(item)) {
            checkboxStates7[item] = false
        }
    }

    var selectedItem by remember { mutableStateOf(items[0]) }

    Box(
        modifier = Modifier
            .background(start_color)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            BarraSuperiore()
            ScrittaIniziale(string = "New Calendar")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(boxcol)
                    .padding(10.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )



                days.forEach { day ->
                    DayBox(day)
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                )
                //SUBMIT

                Row(horizontalArrangement = Arrangement.Center) {
                    Button(

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(start = 15.dp, end = 15.dp)
                            .shadow(elevation = 10.dp, shape = RoundedCornerShape(15.dp)),
                    ) {
                        Text(
                            text = "Submit",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }

                }
            }

            }

        }



}

@Composable
fun DayBox(day: String) {
    var textMin = remember { mutableStateOf("") }
    var textMax = remember { mutableStateOf("") }



    var expanded by remember {
        mutableStateOf(false)
    }

    val items = listOf(
        "Matteo",
        "Carlo",
        "Flacko",
        "Amrando",
        "Francesco",
        "Franco"
    )

    var selectedItem by remember { mutableStateOf(items[0]) }

    val checkboxStates = remember { mutableStateMapOf<String, Boolean>() }
    items.forEach { item ->
        if (!checkboxStates.contains(item)) {
            checkboxStates[item] = false
        }
    }
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {


        Box(modifier = Modifier.width(200.dp)) {

            Button(
                onClick = {
                    expanded = !expanded
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(start_color)
            ) {
                Text(
                    text = day,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }



            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(200.dp)
                    .background(boxcol)
            ) {

                items.forEach { item ->

                    DropdownMenuItem(
                        text = {
                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = item)
                                Spacer(modifier = Modifier.width(10.dp))
                                CheckBox(
                                    checked = checkboxStates[item]
                                        ?: false,
                                    onCheckedChange = { isChecked ->
                                        checkboxStates[item] = isChecked
                                    },
                                    1
                                )
                            }


                        },
                        onClick = {
                            selectedItem = item
                            expanded = false
                        }
                    )


                }
            }


        }


        Spacer(
            modifier = Modifier
                .width(15.dp)
        )

        //MIN
        Box(
            modifier = Modifier
                .height(56.dp)
                .width(60.dp)
                .background(Color.Transparent)
        ) {
            OutlinedTextField(
                value = textMin.value,
                label = {
                    Text(
                        text = "min", style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                },
                onValueChange = { textMin.value = it },
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = 60.dp,
                        minHeight = 40.dp
                    )
                    .wrapContentHeight(
                        Alignment.CenterVertically
                    )
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .fillMaxSize()
                    .fillMaxHeight(),
                singleLine = true,

                textStyle = TextStyle(
                    color = Color.Black, // Imposta il colore del testo
                    fontSize = 14.sp
                )
            )
        }
        Spacer(
            modifier = Modifier
                .width(10.dp)
        )

        //MAX
        Box(
            modifier = Modifier
                .height(56.dp)
                .width(60.dp)
                .background(Color.Transparent)
        ) {
            OutlinedTextField(
                value = textMax.value,
                label = {
                    Text(
                        text = "max", style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                },
                onValueChange = { textMax.value = it },
                modifier = Modifier
                    .wrapContentHeight(
                        Alignment.CenterVertically
                    )
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .fillMaxSize()
                    .fillMaxHeight(),
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black, // Imposta il colore del testo
                    fontSize = 14.sp
                )

            )
        }




    }
}
