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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.speedypizza.R
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.green2
import com.example.speedypizza.ui.theme.start_color




@Composable

fun ExchangeRequests(navController: NavHostController, viewModel: LoginViewModel) {

    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color),
        startY = 0f,
        endY = 2000f
    )

    ConstraintLayout {
        val (box) = createRefs()
        Box(modifier = Modifier
            .background(brush = gradient)
            .fillMaxSize()
            .constrainAs(box) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Column {
                BarraSuperiore(navController, viewModel)
                ScrittaIniziale("Exchange Requests")
                Spacer(modifier = Modifier.height(50.dp))
                RequestsList()
            }
        }

    }

}

@Composable
fun RequestsList(){
    val context= LocalContext.current

    val raiderNames= listOf("Carlo ", "Matteo ", "Flavio ")

    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf("Seleziona un'opzione")
    }

    val myShifts= listOf("Mercoledi' 10:00/13:00", "Venerdi 19:00/21:30", "Sabato 21:00/23:00", "Domenica 21:00/23:00") //qui ci andranno tutti i turni attuali del raider

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(boxcol)
            .padding(10.dp)
    ) {
        Box(
            modifier=Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                contentPadding = PaddingValues(horizontal = 5.dp),
            ) {
                items(raiderNames) { name ->
                    RichiesteItem(name)
                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
        }




        Column(modifier=Modifier.fillMaxHeight()){

            Card(
                modifier= Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(top = 15.dp)
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(20.dp))
//                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
            ){
                Row (
                    horizontalArrangement=Arrangement.Center,
                    modifier= Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(center_color),
                ){
                    val turn=context.getString(R.string.Your_Turns)
                    Text(
                        text=turn,
                        color=Color.White,
                        modifier=Modifier
                            .padding(5.dp),
                        style=TextStyle(fontSize=20.sp, fontWeight = FontWeight.Bold)
                    )


                }
                val checkboxStates1=remember{ mutableStateMapOf<String, Boolean>() }
                myShifts.forEach { shift->
                    if(!checkboxStates1.contains(shift)){
                        checkboxStates1[shift]=false
                    }
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier=Modifier.padding(top=4.dp)
                ) {
                    items(myShifts){ shift->
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment=Alignment.CenterVertically,
                            modifier=Modifier.padding(start=15.dp)
                        ){
                            Box(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(20.dp),
                                contentAlignment = Alignment.Center

                            ){
                                Text(text = shift, style=TextStyle(fontSize=13.sp, fontWeight = FontWeight.Bold))
                            }
                            Spacer(modifier = Modifier.width(140.dp))
                            CheckBox(checked = checkboxStates1[shift] ?:false, onCheckedChange = { isChecked ->checkboxStates1[shift]=isChecked }, 1)
                        }

                    }


                }

            }
            LazyColumn(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(raiderNames) {name ->
                    Turns(name)

                }

            }

            val buttonColor = ButtonDefaults.buttonColors(center_color)
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
                        println("Send only to selected riders")
                    },
                    colors = buttonColor,
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp)
                        .shadow(elevation = 5.dp, shape= CircleShape)
                ){
                    val send=context.getString(R.string.Send)
                    Text(text = send, color=Color.White, style=TextStyle(fontSize=15.sp, fontWeight = FontWeight.Bold))
                }

                Spacer(modifier = Modifier.width(20.dp))


                Button(
                    onClick = {
                        //qui ci va il metodo associato al botone
                        println("Send to All the raiders")
                    },
                    colors = buttonColor,
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp)
                        .shadow(elevation = 5.dp, shape= CircleShape)
                ){
                    val sendAll=context.getString(R.string.Sent_To_All)
                    Text(text = sendAll, color=Color.White, style=TextStyle(fontSize=15.sp, fontWeight = FontWeight.Bold))
                }
            }

        }
    }
}

@Composable
fun CheckBox(checked: Boolean, onCheckedChange: (Boolean) -> Unit, int: Int) {
    Box(
        modifier = Modifier
            .alpha(0.9f)
            .size(18.dp)
            .border(BorderStroke(width = 1.dp, color = Color.LightGray), CircleShape)
            .clip(CircleShape)
            .background(
                if (checked && int == 1) start_color else if (checked && int == 2) Color.Yellow else if (checked && int == 3) Color.Green else Color.White ,
                shape = RoundedCornerShape(8.dp),
            )
            //.shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White)
        } else {
            //non faccio nulla
        }
    }
}

@Composable
fun RichiesteItem(string: String){
    val hisTurn="Monday: 12:00/15:00" //qui ci andra' il turno che gli viene proposto
    val myTurn="Friday: 12:00/15:00" //qui ci andra' quello attuale suo
    Card(
        modifier= Modifier
            .height(130.dp)
            .width(150.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(3.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(3.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_account),
                        contentDescription = "work",
                        modifier = Modifier
                            .size(60.dp),
                        tint = Color.Black
                    )
                    Text(text=string, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier= Modifier
                        .width(25.dp)
                        .height(25.dp),
                ) {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .background(green2),
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
                Box(
                    modifier= Modifier
                        .width(25.dp)
                        .height(25.dp),

                    ) {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .background(center_color),
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.Black
                    )

                }

            }
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = hisTurn,
                color = Color.Black,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold)
            )

            Text(
                text = myTurn,
                color = Color.Black,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold)
            )

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Turns(name: String) {
    val raidersTurn=listOf("Lunedi' 11:00/14:00", "Martedi' 11:00/14:00", "Martedi' 19:00/22:00", "Sabato 12:00/15:00") //qui ci andranno tutti i turni del raider

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var gender by remember {
        mutableStateOf("")
    }

    val textStyle = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold
    )

    Box(
        modifier=Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            TextField(
                value =name.plus(gender),
                onValueChange = {},
                textStyle=textStyle,
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier=Modifier
                    .menuAnchor()
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp) // Imposta il raggio degli angoli desiderato
                    )
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier=Modifier
//                    .align(Alignment.Center)
//                    .background(center_color),
            ) {

                for(turn in raidersTurn){
                    DropdownMenuItem(
                        text = { Text(text = turn) },//al posto di quesi ci andranno i turni presi con la query
                        onClick = {
                            gender = turn
                            isExpanded = false

                        }
                    )
                }

            }
        }

    }
}

/*@Preview
@Composable
fun Preview3() {
    ExchangeRequests(rememberNavController())
}
*/