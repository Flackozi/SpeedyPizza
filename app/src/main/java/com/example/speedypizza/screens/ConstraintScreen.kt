package com.example.speedypizza.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedypizza.R
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color

@Preview
@Composable
fun ConstraintScreen(){
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
            BarraSuperiore()
            ScrittaIniziale(string = "Constraints")
            ElencoGiorni()
        }
    }

}



@Preview
@Composable
fun ElencoGiorni(){
    val buttonColor = ButtonDefaults.buttonColors(start_color)
    Row (//riga di intestazione con le icone
        horizontalArrangement = Arrangement.Center, //distribuisce lo spazio tra i bottoni
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
    ){
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .background(Color.Transparent)

        )
        Spacer(modifier = Modifier.width(15.dp))

        Icon(painter = painterResource(
            id = R.drawable.ic_alert),
            contentDescription ="noWork",
            tint= Color.Red,
            modifier = Modifier
                .size(40.dp)
        )
        Icon(painter = painterResource(
            id = R.drawable.ic_alert),
            contentDescription ="noWork",
            tint = Color.Yellow,
            modifier = Modifier
                .size(40.dp)
        )
        Icon(painter = painterResource(
            id = R.drawable.ic_alert),
            contentDescription ="noWork",
            tint = Color.Green,
            modifier = Modifier
                .size(40.dp)
        )
    }
    Row(
        horizontalArrangement = Arrangement.Center, //distribuisce lo spazio tra i bottoni
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        val checkboxStates1=remember{ mutableStateMapOf<String, Boolean>() }
        val checkboxStates2=remember{ mutableStateMapOf<String, Boolean>() }
        val checkboxStates3=remember{ mutableStateMapOf<String, Boolean>() }


        //Colonna giorni della settimana
        val items = listOf("Monday", "Tuesday", "Wednesday","Thursday","Friday", "Saturday", "Sunday")
        items.forEach {item->
            if(!checkboxStates1.contains(item)){
                checkboxStates1[item]=false
            }
        }
        items.forEach {item->
            if(!checkboxStates2.contains(item)){
                checkboxStates2[item]=false
            }
        }
        items.forEach {item->
            if(!checkboxStates3.contains(item)){
                checkboxStates3[item]=false
            }
        }

        LazyColumn(modifier=Modifier
            .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp)),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)

        ) {
            items(items) { item ->
                Row(
                    Modifier.padding(1.dp),
                    verticalAlignment=Alignment.CenterVertically
                ){
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(50.dp)
                            .shadow(elevation = 5.dp, shape = RoundedCornerShape(8.dp))
                            .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center

                    ) {
                        Text(text = item, color=Color.Black, style=TextStyle(fontSize=20.sp, fontWeight = FontWeight.Bold))
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    CustomCheckbox(checked = checkboxStates1[item] ?:false, onCheckedChange = { isChecked ->checkboxStates1[item]=isChecked }, 1)
                    Spacer(modifier = Modifier.width(10.dp))
                    CustomCheckbox(checked = checkboxStates2[item] ?:false, onCheckedChange = { isChecked ->checkboxStates2[item]=isChecked }, 2)
                    Spacer(modifier = Modifier.width(10.dp))
                    CustomCheckbox(checked = checkboxStates3[item] ?:false, onCheckedChange = { isChecked ->checkboxStates3[item]=isChecked }, 3)

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
            Text(text = "Submit", color=Color.White, style=TextStyle(fontSize=15.sp, fontWeight = FontWeight.Bold))
        }
    }
}

@Composable
fun CustomCheckbox(checked: Boolean, onCheckedChange: (Boolean) -> Unit, int: Int) {
    Box(
        modifier = Modifier
            .size(30.dp)
            //.clip(CircleShape)
            .background(
                if (checked && int == 1) Color.Red else if (checked && int == 2) Color.Yellow else if (checked && int == 3) Color.Green else Color.LightGray ,
                shape = RoundedCornerShape(8.dp)
            )
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
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
