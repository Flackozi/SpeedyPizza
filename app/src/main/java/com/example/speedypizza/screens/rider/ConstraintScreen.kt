package com.example.speedypizza.screens.rider

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.speedypizza.R
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.gialloScuro
import com.example.speedypizza.ui.theme.green2
import com.example.speedypizza.ui.theme.grigiochiarissimo
import com.example.speedypizza.ui.theme.start_color





@Composable
fun ConstraintScreen(navController: NavHostController, close: () -> Unit = {}) {
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
            BarraSuperiore(navController)
            ScrittaIniziale(string = "Constraints")
            Spacer(modifier=Modifier.height(100.dp))
            ElencoGiorni()
        }
    }

}




@Composable
fun ElencoGiorni(){
    val buttonColor = ButtonDefaults.buttonColors(start_color)
    val context= LocalContext.current
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
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(30.dp)
                    .background(Color.Transparent)

            )
            Spacer(modifier = Modifier.width(15.dp))

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
        Row(
            horizontalArrangement = Arrangement.Center, //distribuisce lo spazio tra i bottoni
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            val checkboxStates1 = remember { mutableStateMapOf<String, Boolean>() }
            val checkboxStates2 = remember { mutableStateMapOf<String, Boolean>() }
            val checkboxStates3 = remember { mutableStateMapOf<String, Boolean>() }


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
            val items =
                listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

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
                modifier = Modifier
                    .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp)),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)

            ) {
                items(days) { day ->
                    val dayString = context.getString(day)
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
                                checkboxStates1[dayString] = isChecked
                            },
                            1
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        CustomCheckbox(
                            checked = checkboxStates2[dayString] ?: false,
                            onCheckedChange = { isChecked ->
                                checkboxStates2[dayString] = isChecked
                            },
                            2
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        CustomCheckbox(
                            checked = checkboxStates3[dayString] ?: false,
                            onCheckedChange = { isChecked ->
                                checkboxStates3[dayString] = isChecked
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
                    //qui ci va il metodo associato al botone
                    println("Exchange requests")
                },
                colors =buttonColor,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
                    .shadow(elevation = 5.dp, shape= CircleShape)
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
            //.clip(CircleShape)
            .background(
                if (checked && int == 1) Color.Red else if (checked && int == 2) Color.Yellow else if (checked && int == 3) Color.Green else grigiochiarissimo ,
                shape = RoundedCornerShape(11.dp),
        )
            //.shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.Black)
        } else {
            //non faccio nulla
        }
    }
}

@Preview
@Composable
fun Preview2() {
    ConstraintScreen(rememberNavController())
}

