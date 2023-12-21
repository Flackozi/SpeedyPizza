package com.example.speedypizza.screens.rider


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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.speedypizza.R
import com.example.speedypizza.entity.Exchanges
import com.example.speedypizza.entity.Shifts
import com.example.speedypizza.screens.rider.GlobalExchangeVariables.shiftsList
import com.example.speedypizza.screens.viewmodel.ExchangeViewModel
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.green2
import com.example.speedypizza.ui.theme.start_color
import java.lang.reflect.Field


@RequiresApi(Build.VERSION_CODES.Q)
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



object GlobalExchangeVariables {
    //questa variabile servirà a tenere il valore dei vincoli associati ai giorni della settimana
    var checkBoxValue=0
    val shiftsList: MutableList<Shifts> = mutableListOf()
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RequestsList(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    viewModel: ExchangeViewModel,
    nickname: String
) {
    val context= LocalContext.current


    //faccio una query per prendere tutti i nomi dei raider
    val nicknamesList = viewModel.myRiders!!.map { user -> user.nickname }
    //faccio una query per prendere i turni di tutti i rider

    val allShifts = viewModel.riderShifts!!.map { shifts ->  shifts.copy()}
    val myShifts = allShifts.filter { it.rider == nickname }

    val myReceivedRequests = viewModel.receivedRequests?.map { receivedRequest -> receivedRequest.copy() }
    Column(
        horizontalAlignment = CenterHorizontally,
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
                if (myReceivedRequests != null) {
                    items(myReceivedRequests) { exchange ->

                        RichiesteItem(loginViewModel.loggedUser!!.nickname, exchange.sender,  exchange.receiverShift, exchange.senderShift, viewModel, navController)
                        Spacer(modifier = Modifier.width(10.dp))

                    }
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
                myShifts.forEach { shift->
                    if(!checkboxStates1.contains(shift.day)){
                        checkboxStates1[shift.day]=false
                    }
                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier=Modifier.padding(top=4.dp)
                ) {
                    itemsIndexed(myShifts) { index, shift ->
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

                                Text(text = getDay(shift.day) , style=TextStyle(fontSize=15.sp, fontWeight = FontWeight.Bold))
                            }
                            Spacer(modifier = Modifier.width(100.dp))
                            CheckBox(
                                checked = checkboxStates1[shift.day] ?: false,
                                onCheckedChangeWithIndex = { isChecked, index ->
                                    checkboxStates1[shift.day] = isChecked
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
                items(items = nicknamesList) { name ->
                    if(name!=nickname){
                        Turns(name, allShifts.filter { it.rider == name })
                    }

                }

            }

            val buttonColor = ButtonDefaults.buttonColors(center_color)

            //BOTTONE SEND
            Row(
                horizontalArrangement = Arrangement.Center, //distribuisce lo spazio tra i bottoni
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ){
                Button(
                    onClick = {
                        println(myShifts)
                        val senderTurn = myShifts[GlobalExchangeVariables.checkBoxValue].day
                        // Verifica se senderTurn è diverso da tutti gli elementi shift.day in shiftsList
                        var shouldSendRequest = shiftsList.all { it.day != senderTurn }

                        if (shouldSendRequest) {
                            for (shift in shiftsList) {
                                if (shift.day == senderTurn) {
                                    shouldSendRequest = false
                                    break
                                }
                            }
                        }

                        val myTurn=myShifts.map{it.day}

                        if (shouldSendRequest) {
                            for (shift in shiftsList) {
                                if(shift.day !in myTurn) {//controllo che shift.day non faccia gia' parte dei miei turni
                                    viewModel.sendRequest(
                                        Exchanges(
                                            nickname,
                                            shift.rider,
                                            senderTurn,
                                            shift.day
                                        )
                                    )
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
                    val send=context.getString(R.string.Send)
                    Text(text = send, color=Color.White, style=TextStyle(fontSize=15.sp, fontWeight = FontWeight.Bold))
                }

                Spacer(modifier = Modifier.width(20.dp))

                //BOTTONE SEND TO ALL
                Button(
                    onClick = {
                        val senderTurn=myShifts[GlobalExchangeVariables.checkBoxValue].day
                        //prima mi devo prendere la lista di tutti i rider
                        for(riderName in nicknamesList){
                            if(riderName!=nickname) {

                                val riderTurns = allShifts.filter { it.rider == riderName }

                                for (shift in riderTurns) {

                                    if (senderTurn!= shift.day) {

                                        viewModel.sendRequest(Exchanges(nickname, riderName, myShifts[GlobalExchangeVariables.checkBoxValue].day, shift.day))
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
            .clickable {
                if (!selected) {
                    onCheckedChangeWithIndex(!checked, index)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White)
        }
    }
}

fun onCheckBoxClicked(index: Int) {
    // Ora puoi fare qualcosa con l'indice della CheckBox selezionata
    GlobalExchangeVariables.checkBoxValue=index
    Log.d("valore della checkBox", GlobalExchangeVariables.checkBoxValue.toString())
}

@Composable
fun RichiesteItem(
    nickname: String,
    senderName: String,
    receiverShift: String,
    senderShift: String,
    viewModel: ExchangeViewModel,
    navController: NavHostController
){

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
                Column(horizontalAlignment = CenterHorizontally) {
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
                            viewModel.updateShift(senderName, receiverShift, senderShift)
                            //aggiorna il turno del rider che ha inviato la richiesta
                            viewModel.updateShift(nickname, senderShift, receiverShift)
                            //bisogna eliminare le altre richieste riguardanti rider che ha inviato lo scambio che icnludevano lo stesso giorno
                            viewModel.deleteOtherRequest(senderName, senderShift)
                            //bisogna eliminare le richieste che il rider loggato ha inviato che includevano il giorno appena scambiato
                            viewModel.deleteOtherRequest(nickname, receiverShift)

                            navController.navigate("exchangePage")
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
                            viewModel.deleteRequest(
                                nickname,
                                senderName,
                                senderShift,
                                receiverShift
                            )
                            navController.navigate("exchangePage")
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
                text = stringResource(id = R.string.His).plus(": ").plus(getDay(senderShift)),
                color = Color.Black,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold),
                modifier=Modifier.padding(start = 10.dp)
            )



            Text(
                text = stringResource(id = R.string.Your).plus(": ").plus(getDay(receiverShift)),
                color = Color.Black,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold),
                modifier=Modifier.padding(start = 10.dp)
            )



        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Turns(name: String, shiftList: List<Shifts>) {



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

    var dayTurn by remember {
        mutableStateOf("")
    }

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
            ) {
                val raidersTurn= shiftList.map { shift -> shift.day }
                println(raidersTurn)

                for(turn in raidersTurn){
                    val day = getDay(day = turn)
                    DropdownMenuItem(
                        text = { Text(text = day) },//al posto di quesi ci andranno i turni presi con la query
                        onClick = {
                            dayTurn = turn
                            gender = day
                            isExpanded = false

                        }
                    )
                }

            }
        }
    }

    //questo blocco serve ad aggiungere alla lista dei turni con cui si vuole proporre lo scambio la coppia nome e turno appena selezionata
    if(gender.isNotBlank()) {

        //assegna all'indiceEsistente l'indice della prima coppia all'interno di shiftsList in cui il primo elemento della coppia è uguale al valore di name
        val indiceEsistente = shiftsList.indexOfFirst { it.rider == name }

        if (indiceEsistente != -1) {
            // Sovrascrivi la coppia esistente
            shiftsList.add(indiceEsistente, Shifts(name, dayTurn))
        } else {
            // Aggiungi una nuova coppia
            shiftsList.add(Shifts(name, dayTurn))
        }
    }
}

@Composable
fun getDay(day: String): String{

    val dayField: Field = R.string::class.java.getDeclaredField(day)

    val dayId: Int = dayField.getInt(dayField)

    return LocalContext.current.getString(dayId)

}