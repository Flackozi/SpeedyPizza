package com.example.speedypizza.screens.rider

import android.util.Log
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.speedypizza.R
import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.screens.rider.GlobalVariables.checkBoxValues
import com.example.speedypizza.viewmodel.ConstraintViewModel
import com.example.speedypizza.viewmodel.GeneralException
import com.example.speedypizza.viewmodel.LoginViewModel
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.gialloScuro
import com.example.speedypizza.ui.theme.green2
import com.example.speedypizza.ui.theme.grigiochiarissimo
import com.example.speedypizza.ui.theme.start_color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ConstraintScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    constraintViewModel: ConstraintViewModel
) {
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
            BarraSuperiore(navController, loginViewModel)
            ScrittaIniziale(string = stringResource(R.string.Constraints))
            Spacer(modifier=Modifier.height(100.dp))
            ElencoGiorni(navController, constraintViewModel, loginViewModel.loggedUser!!.nickname)
        }
    }

}


object GlobalVariables {
    //questa variabile servirà a tenere il valore dei vincoli associati ai giorni della settimana
    val checkBoxValues = MutableList(7) { 0 }
}

@Composable
fun ElencoGiorni(
    navController: NavHostController,
    constraintViewModel: ConstraintViewModel,
    nickname: String
) {
    val buttonColor = ButtonDefaults.buttonColors(start_color)
    val context= LocalContext.current

    val checkboxStates1 = remember { mutableStateMapOf<String, Boolean>() }
    val checkboxStates2 = remember { mutableStateMapOf<String, Boolean>() }
    val checkboxStates3 = remember { mutableStateMapOf<String, Boolean>() }

    val textMin = remember { mutableStateOf("") }
    val textMax = remember { mutableStateOf("") }

    //Colonna giorni della settimana
    val days = listOf(
        R.string.Monday,
        R.string.Tuesday,
        R.string.Wednesday,
        R.string.Thursday,
        R.string.Friday,
        R.string.Saturday,
        R.string.Sunday
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(boxcol)
            .padding(10.dp)


    ) {
        Row(//riga di intestazione con le icone
            horizontalArrangement = Arrangement.Center, //distribuisce lo spazio tra i bottoni
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
                .offset(y = 15.dp)
        ) {
            //MIN
            Box(
                modifier = Modifier
                    .height(56.dp)
                    .width(60.dp)
                    .offset(x = 10.dp)
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
                    .offset(x = 10.dp)
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

            Spacer(modifier= Modifier
                .width(25.dp)
                .height(15.dp))

            Icon(
                painter = painterResource(
                    id = R.drawable.ic_alert
                ),
                contentDescription = "noWork",
                tint = center_color,
                modifier = Modifier
                    .size(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                painter = painterResource(
                    id = R.drawable.ic_alert
                ),
                contentDescription = "noWork",
                tint = gialloScuro,
                modifier = Modifier
                    .size(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                painter = painterResource(
                    id = R.drawable.ic_alert
                ),
                contentDescription = "noWork",
                tint = green2,
                modifier = Modifier
                    .size(30.dp)
            )
        }

        Spacer(modifier=Modifier.height(15.dp))

        Row(
            horizontalArrangement = Arrangement.Center, //distribuisce lo spazio tra i bottoni
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {


            days.forEach { day ->
                val dayString = context.getString(day)
                if (!checkboxStates1.contains(dayString)) {
                    checkboxStates1[dayString] = false
                }
            }
            days.forEach { day ->
                val dayString = context.getString(day)
                if (!checkboxStates2.contains(dayString)) {
                    checkboxStates2[dayString] = false
                }
            }
            days.forEach { day ->
                val dayString = context.getString(day)
                if (!checkboxStates3.contains(dayString)) {
                    checkboxStates3[dayString] = false
                }
            }

            LazyColumn(
                modifier = Modifier.background(color = Color.Transparent, shape = RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(days) { giorno ->
                    val dayString = context.getString(giorno)
                    val index = days.indexOfFirst { context.getString(it) == dayString }
                    Row(
                        Modifier.padding(1.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp)
                                .shadow(elevation = 5.dp, shape = RoundedCornerShape(20.dp))
                                .background(center_color, shape = RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(
                                text = dayString,
                                color = Color.White,
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        CustomCheckbox(
                            checked = checkboxStates1[dayString] ?: false,
                            onCheckedChange = { isChecked ->
                                //questo lo faccio per far si che solo una delle tre possa essere selezionata
                                checkboxStates1[dayString] = isChecked
                                checkboxStates2[dayString] = false
                                checkboxStates3[dayString] = false
                                checkBoxValues[index]=1//setto il valore della checkBox del giorno con il valore 1

                            },
                            1
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        CustomCheckbox(
                            checked = checkboxStates2[dayString] ?: false,
                            onCheckedChange = { isChecked ->
                                checkboxStates1[dayString] = false
                                checkboxStates2[dayString] = isChecked
                                checkboxStates3[dayString] = false
                                checkBoxValues[index]=2//setto il valore della checkBox del giorno con il valore 2
                            },
                            2
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        CustomCheckbox(
                            checked = checkboxStates3[dayString] ?: false,
                            onCheckedChange = { isChecked ->
                                checkboxStates1[dayString] = false
                                checkboxStates2[dayString] = false
                                checkboxStates3[dayString] = isChecked
                                checkBoxValues[index]=3//setto il valore della checkBox del giorno con il valore 3
                            },
                            3
                        )

                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center, //distribuisce lo spazio tra i bottoni
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ){
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        val min: Int
                        val max: Int
                        try {
                            if (textMin.value.isBlank() || textMax.value.isBlank()) {
                                throw GeneralException("Non tutti i dati sono stati inseriti")
                            }

                            min = textMin.value.toInt()
                            max = textMax.value.toInt()

                            if (min > max) {
                                throw GeneralException("Min non può essere maggiore di max")
                            }


                            constraintViewModel.submit(
                                Constraints(
                                    nickname,
                                    max,
                                    min,
                                    checkBoxValues[0],
                                    checkBoxValues[1],
                                    checkBoxValues[2],
                                    checkBoxValues[3],
                                    checkBoxValues[4],
                                    checkBoxValues[5],
                                    checkBoxValues[6]
                                )
                            )

                            navController.navigate("riderHome")
                        } catch (e: GeneralException) {
                            navController.navigate("constraintsPage")
                            Log.e("InsertError", "Inserimento parametri errato: ${e.message}")
                        }
                    }
                },
                colors = buttonColor,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
                    .shadow(elevation = 0.dp, shape = CircleShape)
            ){
                val submit=context.getString(R.string.Submit)
                Text(text = submit, color=Color.White, style=TextStyle(fontSize=15.sp, fontWeight = FontWeight.Bold))
            }
        }
    }

}




@Composable
fun CustomCheckbox(checked: Boolean, onCheckedChange: (Boolean) -> Unit, int: Int) {
    Box(
        modifier = Modifier
            .alpha(0.9f)
            .size(27.dp)
            .border(BorderStroke(width = 2.dp, color = center_color), CircleShape)
            .background(
                if (checked && int == 1) center_color else if (checked && int == 2) gialloScuro else if (checked && int == 3) green2 else grigiochiarissimo,
                shape = RoundedCornerShape(11.dp),
            )
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.Black)
        }
    }
}



