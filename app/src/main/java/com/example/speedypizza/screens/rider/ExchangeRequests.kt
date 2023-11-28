package com.example.speedypizza.screens.rider


import android.util.Log
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
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.speedypizza.R
import com.example.speedypizza.screens.rider.globalExchangeVariables.shiftsList
import com.example.speedypizza.screens.viewmodel.ExchangeViewModel
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.green2
import com.example.speedypizza.ui.theme.start_color
import kotlinx.coroutines.delay
import java.util.Arrays


@Composable
fun ExchangeRequests(
    navController: NavHostController,
    viewModel: LoginViewModel,
    exchangeViewModel: ExchangeViewModel ) {

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
                RequestsList(navController, viewModel, exchangeViewModel, viewModel.loggedUser!!.nickname)
            }
        }

    }

}



object globalExchangeVariables {
    //questa variabile servirà a tenere il valore dei vincoli associati ai giorni della settimana
    var checkBoxValue=0
    val shiftsList: MutableList<Pair<String, String>> = mutableListOf()
}


@Composable
fun RequestsList(
    navController: NavHostController,
    LoginViewModel: LoginViewModel,
    viewModel: ExchangeViewModel,
    nickname: String
) {
    val context= LocalContext.current

    //faccio una query per prendere tutti i nomi dei raider
    viewModel.retrieveMyRider()
    val nicknamesList = viewModel.myRiders?.map { user -> user.nickname }

    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf("Seleziona un'opzione")
    }

    //faccio una query per prendere i turni del rider che ha eseguito l'accesso
    viewModel.retrieveMyShifts(nickname)
    val myShifts= viewModel.myShifts?.map { shift -> shift.day }

    //faccio una query per prendere le richieste ricevuto dal rider
    viewModel.retrieveExchange(nickname)
    val receivedRequestsSender=viewModel.receivedRequests?.map{request-> request.sender}
    val receivedRequestsMyShift=viewModel.receivedRequests?.map{request-> request.receiverShift}
    val receivedRequestsSenderShift=viewModel.receivedRequests?.map{request-> request.senderShift}






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
                itemsIndexed(receivedRequestsSender.orEmpty()) { index, name ->
                    val senderShift=receivedRequestsSenderShift?.get(index).toString()
                    val receiverShift=receivedRequestsMyShift?.get(index).toString()
                    RichiesteItem(nickname, name,  receiverShift, senderShift, viewModel)
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }


        var selectedCheckBoxIndex by remember { mutableIntStateOf(-1) }


        Column(modifier=Modifier.fillMaxHeight()){

            Card(
                modifier= Modifier
                    .width(300.dp)
                    .height(150.dp)
                    .padding(top = 15.dp)
                    .align(CenterHorizontally)
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(20.dp)),
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
                myShifts?.forEach { shift->
                    if(!checkboxStates1.contains(shift)){
                        checkboxStates1[shift]=false
                    }
                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier=Modifier.padding(top=4.dp)
                ) {
                    itemsIndexed(items = myShifts.orEmpty()) { index, shift ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment=Alignment.CenterVertically,
                            modifier=Modifier.padding(start = 5.dp)
                        ){
                            Box(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(20.dp),
                                contentAlignment = Alignment.Center

                            ){
                                Text(text = shift, style=TextStyle(fontSize=15.sp, fontWeight = FontWeight.Bold))
                            }
                            Spacer(modifier = Modifier.width(100.dp))
                            CheckBox(
                                checked = checkboxStates1[shift] ?: false,
                                onCheckedChangeWithIndex = { isChecked, index ->
                                    checkboxStates1[shift] = isChecked
                                    selectedCheckBoxIndex = if (isChecked) index else -1
                                    onCheckBoxClicked(index)
                                },
                                index = index,
                                selected = index == selectedCheckBoxIndex
                            )
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
                itemsIndexed(items = nicknamesList.orEmpty()) {index, name ->
                    if(name!=nickname){
                        Turns(name, viewModel, index)
                    }

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
//                        println(globalExchangeVariables.shiftsList)
                        for ((name, shift) in shiftsList) {
                            if (myShifts != null && myShifts.get(globalExchangeVariables.checkBoxValue)!=shift) {
//                                Log.d("dati per la query", nickname)
//                                Log.d("dati per la query", myShifts.get(globalExchangeVariables.checkBoxValue))
//                                Log.d("dati per la query", name)
//                                Log.d("dati per la query", shift)

                                viewModel.sendRequest(nickname, myShifts.get(globalExchangeVariables.checkBoxValue),name, shift)
                            }
                        }
                        navController.navigate("riderHome")
                    },
                    colors = buttonColor,
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp)
                        .shadow(elevation = 5.dp, shape = CircleShape)
                ){
                    val send=context.getString(R.string.Send)
                    Text(text = send, color=Color.White, style=TextStyle(fontSize=15.sp, fontWeight = FontWeight.Bold))
                }

                Spacer(modifier = Modifier.width(20.dp))


                Button(
                    onClick = {
                        //qui mi setto una variabile con il nome del turno che il rider vuole scambiare
                        val senderShift=myShifts!![globalExchangeVariables.checkBoxValue]
                        var turns: List<String>?=null
                        //prima mi devo prendere la lista di tutti i rider
                        for(riderName in nicknamesList!!){
                            if(riderName!=nickname) {
                                println(riderName)
                                viewModel.retrieveRiderShifts(riderName)
                                turns = viewModel.riderTurns
                                println(riderName)
                                Log.d("turni pre", "Lista di stringhe pre: ${turns?.joinToString()}")
                                Log.d("turni post", "Lista di stringhe post: ${turns?.joinToString()}")
                                //println(turns)
                                //per ogni rider prendere la lista dei propri turni e inviare una richiesta di scambio per ogni turno diverso da quello che si vuole scambiare
                                if (turns != null) {
                                    for (shift in turns) {
                                        if (senderShift != shift) {
                                            viewModel.sendRequest(nickname, senderShift, riderName, shift)
                                        }
                                    }
                                }
                            }
                        }
                        navController.navigate("riderHome")
                    },
                    colors = buttonColor,
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp)
                        .shadow(elevation = 5.dp, shape = CircleShape)
                ){
                    val sendAll=context.getString(R.string.Sent_To_All)
                    Text(text = sendAll, color=Color.White, style=TextStyle(fontSize=15.sp, fontWeight = FontWeight.Bold))
                }
            }

        }
    }
}

@Composable
//fun CheckBox(checked: Boolean, onCheckedChange: (Boolean) -> Unit, int: Int) {
fun CheckBox(checked: Boolean,
             onCheckedChangeWithIndex: (Boolean, Int) -> Unit,
             index: Int,
             selected: Boolean
) {

    Box(
        modifier = Modifier
            .alpha(0.9f)
            .size(20.dp)
            .border(BorderStroke(width = 1.dp, color = Color.LightGray), CircleShape)
            .clip(CircleShape)
            .background(
                start_color,
                shape = RoundedCornerShape(8.dp),
            )
            //.shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
            .clickable {
                if (!selected) {
                    onCheckedChangeWithIndex(!checked, index)
                }
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

fun onCheckBoxClicked(index: Int) {
    // Ora puoi fare qualcosa con l'indice della CheckBox selezionata
    globalExchangeVariables.checkBoxValue=index
    Log.d("valore della checkBox", globalExchangeVariables.checkBoxValue.toString())
}

@Composable
fun RichiesteItem(
    nickname: String,
    senderName: String,
    receiverShift: String,
    senderShift: String,
    viewModel: ExchangeViewModel
){
    //val hisTurn="Monday: 12:00/15:00" //qui ci andra' il turno che gli viene proposto
    //val myTurn="Friday: 12:00/15:00" //qui ci andra' quello attuale suo
    Card(
        modifier= Modifier
            .height(130.dp)
            .width(150.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),

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
                    Text(text=senderName, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }

                //box in cui il rider accetta il turno
                Box(
                    modifier= Modifier
                        .width(25.dp)
                        .height(25.dp)
                        .clickable(onClick = {
                            Log.d("rider", senderName)
                            Log.d("day", senderShift)

                            //aggiorna il turno al rider che ha accettato
                            viewModel.updateShift(senderName, receiverShift, senderShift,)
                            //aggiorna il turno del rider che ha inviato la richiesta
                            viewModel.updateShift(nickname, senderShift, receiverShift)
                            //bisogna eliminare le altre richieste riguardanti rider che ha inviato lo scambio che icnludevano lo stesso giorno
                            viewModel.deleteOtherRequest(senderName, senderShift)
                            //bisogna eliminare le richieste che il rider loggato ha inviato che includevano il giorno appena scambiato
                            viewModel.deleteOtherRequest(nickname, receiverShift)
                        }),
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

                //box in cui rifiuta la richiesta
                Box(
                    modifier= Modifier
                        .width(25.dp)
                        .height(25.dp)
                        .clickable(onClick = {
                            //cancella la richiesta dal db
                            viewModel.deleteRequest(nickname, senderName, senderShift, receiverShift)
                        }),

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
                text = "His: ".plus(senderShift),
                color = Color.Black,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold),
                modifier=Modifier.padding(start = 10.dp)
            )
            //Log.d("you", senderShift)


            Text(
                text = "Your: ".plus(receiverShift),
                color = Color.Black,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold),
                modifier=Modifier.padding(start = 10.dp)
            )
            //Log.d("his", receiverShift)


        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Turns(name: String, viewModel: ExchangeViewModel, index: Int) {
    //val raidersTurn=listOf("Lunedi", "Martedi", "Mercoledi", "Sabato") //qui ci andranno tutti i turni del raider
    viewModel.retrieveRiderShifts(name)


    var isExpanded by remember {
        mutableStateOf(false)
    }

    var gender by remember {
        mutableStateOf("")
    }

    val textStyle = TextStyle(
        fontSize = 15.sp,
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
                value =name.plus("->").plus(gender),
                onValueChange = {},
                textStyle=textStyle,
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier= Modifier
                    .menuAnchor()
                    .border(
                        width = 2.dp,
                        color = center_color,
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
                val raidersTurn= viewModel.riderShifts?.map { shift -> shift.day }
                println(raidersTurn)

                for(turn in raidersTurn.orEmpty()){
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
    //globalExchangeVariables.shiftsList.add(name to gender)
    //questo blocco serve ad aggiungere alla lista dei turni con cui si vuole proporre lo scambio la coppia nome e turno appena selezionata
    if(gender.isNotBlank()) {
        //assegna all'indiceEsistente l'indice della prima coppia all'interno di shiftsList in cui il primo elemento della coppia è uguale al valore di name
        val indiceEsistente = globalExchangeVariables.shiftsList.indexOfFirst { it.first == name }

        if (indiceEsistente != -1) {
            // Sovrascrivi la coppia esistente
            globalExchangeVariables.shiftsList[indiceEsistente] = name to gender
        } else {
            // Aggiungi una nuova coppia
            globalExchangeVariables.shiftsList.add(name to gender)
        }
    }
}