package com.example.speedypizza.screens.admin

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.speedypizza.entity.Days
import com.example.speedypizza.entity.Shifts
import com.example.speedypizza.screens.rider.BarraSuperiore
import com.example.speedypizza.screens.rider.ScrittaIniziale
import com.example.speedypizza.screens.viewmodel.CalendarViewModel
import com.example.speedypizza.screens.viewmodel.ConstraintViewModel
import com.example.speedypizza.screens.viewmodel.GeneralException
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.screens.viewmodel.MyRiderViewModel
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.green2
import com.example.speedypizza.ui.theme.start_color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.Q)
@Composable


fun CreateCalendar(
    navController: NavHostController,
    viewModel: LoginViewModel,
    myRiderViewModel: MyRiderViewModel,
    createCalendar: CalendarViewModel,
    constraintViewModel: ConstraintViewModel
) {


    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    val myRider = myRiderViewModel.myRiders!!.filter {it.role == 1}.map { it.nickname }
    val dayList = mutableListOf<Days>()
    val shiftList = mutableListOf<Shifts>()

    Box(
        modifier = Modifier
            .background(start_color)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            BarraSuperiore(navController, viewModel)
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
                    val scheduleItem = DayBox(day, myRider, constraintViewModel)
                    dayList.add(scheduleItem.first)
                    scheduleItem.second.forEach {shift ->
                        shiftList.add(shift)
                    }
                    //scheduleItemList.add(scheduleItem)
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
                        onClick = {

                            CoroutineScope(Dispatchers.Main).launch {
                                try{
                                    for(day in dayList){
                                        if(day.min>day.max){
                                            throw GeneralException("Min non può essere maggiore di max")
                                        }
                                    }
                                    createCalendar.newCalendar(dayList, shiftList)
                                    navController.navigate("adminHome")
                                }catch(e: GeneralException){
                                    navController.navigate("createCalendarPage")
                                    Log.e("InsertError", "Inserimento parametri errato: ${e.message}")
                                }
                            }
                        },
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
fun DayBox(day: String, myRider: List<String>, constraintViewModel: ConstraintViewModel): Pair<Days, List<Shifts>> {

    var textMin = remember { mutableStateOf("") }
    var textMax = remember { mutableStateOf("") }
    var listaShift: List<Shifts>
    var constraintsMin: List<Int>?
    var constraintsMax: List<Int>?
    var constraintDay: List<Int>?

    var expanded by remember {
        mutableStateOf(false)
    }


    val selectedRiders = remember { mutableStateListOf<String>() }


    val checkboxStates = remember { mutableStateMapOf<String, Boolean>() }
    myRider.forEach { item ->
        if (!checkboxStates.contains(item)) {
            checkboxStates[item] = false
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {


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
                    .width(250.dp)
                    .background(boxcol)
            ) {

                myRider.forEach { item ->


                    constraintsMin =
                        constraintViewModel.con?.filter { it.nickname == item }?.map { it.min }
                    constraintsMax =
                        constraintViewModel.con?.filter { it.nickname == item }?.map { it.max }
                    constraintDay =
                        constraintViewModel.con?.filter { it.nickname == item }?.mapNotNull {
                            when (day) {
                                "Lunedi", "Monday" -> it.lunedi
                                "Martedi", "Tuesday" -> it.martedi
                                "Mercoledi", "Wednesday" -> it.mercoledi
                                "Giovedi", "Thursday" -> it.giovedi
                                "Venerdi", "Friday" -> it.venerdi
                                "Sabato", "Saturday" -> it.sabato
                                "Domenica", "Sunday" -> it.domenica
                                else -> null  // Ritorna null se il giorno non corrisponde a nessuna colonna
                            }
                        }

                    //constraintDay --> 1 No, -->2 possNo, -->3 Si

                    DropdownMenuItem(
                        text = {
                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                if (constraintDay != null && constraintDay!!.isNotEmpty()) {
                                    when (constraintDay!![0]) {
                                        1 -> Text(
                                            text = item,
                                            color = start_color,
                                            fontWeight = FontWeight.Bold
                                        )

                                        2 -> Text(
                                            text = item,
                                            color = end_color,
                                            fontWeight = FontWeight.Bold
                                        )

                                        3 -> Text(
                                            text = item,
                                            color = green2,
                                            fontWeight = FontWeight.Bold
                                        )

                                        else -> Text(text = item)
                                    }
                                } else {
                                    Text(text = item)
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "min: ${constraintsMin?.joinToString(", ")}")
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "max: ${constraintsMax?.joinToString(", ")}")
                                Spacer(modifier = Modifier.width(5.dp))
                                CheckBox(
                                    checked = checkboxStates[item] ?: false,
                                    onCheckedChange = { isChecked ->
                                        checkboxStates[item] = isChecked
                                        if (isChecked) {
                                            selectedRiders.add(item)
                                        } else {
                                            selectedRiders.remove(item)
                                        }
                                    },
                                    1
                                )
                            }


                        },
                        onClick = {
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
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
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
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )

            )
        }

    }

    listaShift = selectedRiders.map { rider ->
        Shifts(rider, day)

    }


    return Pair(
        Days(
            day,
            if (textMin.value.isEmpty()) 0 else textMin.value.toInt(),
            if (textMax.value.isEmpty()) 0 else textMax.value.toInt()
        ), listaShift
    )//ScheduleItem(day, selectedRiders.toList(), textMin.value, textMax.value)
}

@Composable
fun CheckBox(checked: Boolean, onCheckedChange: (Boolean) -> Unit, int: Int) {
    Box(
        modifier = Modifier
            .alpha(0.9f)
            .size(20.dp)
            .border(BorderStroke(width = 1.dp, color = Color.LightGray), CircleShape)
            .clip(CircleShape)
            .background(
                if (checked && int == 1) start_color else if (checked && int == 2) Color.Yellow else if (checked && int == 3) Color.Green else Color.White,
                shape = RoundedCornerShape(8.dp),
            )
            //.shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
            .clickable {
                onCheckedChange(!checked)
                if(checked) print("true") else print("false")

            },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White)
        } else {
            //non faccio nulla
        }
    }
}
/*
@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun CalendarPreview() {
    CreateCalendar(rememberNavController(), user)
}
*/