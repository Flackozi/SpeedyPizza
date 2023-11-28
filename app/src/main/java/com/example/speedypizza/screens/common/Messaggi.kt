package com.example.speedypizza.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.speedypizza.R
import com.example.speedypizza.entity.Message
import com.example.speedypizza.screens.rider.BarraSuperiore
import com.example.speedypizza.screens.rider.ScrittaIniziale
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.screens.viewmodel.MessageViewModel
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color





@Composable
fun SchermataMessaggi(
    navController: NavHostController,
    viewModel: LoginViewModel,
    messageViewModel: MessageViewModel
) {

    /*var elencoMessaggi = listOf(
        Message(1, "Nuovi turni disponibili", "21/10/2023", 1),
        Message(2, "Calendario dei turni pubblicato", "19/10/2023", 2),
        Message(3, "Nuovi turni disponibili", "21/10/2023", 1),
        Message(4, "Calendario dei turni pubblicato", "19/10/2023", 2),
        Message(1, "Nuovi turni disponibili", "21/10/2023", 1),
        Message(2, "Calendario dei turni pubblicato", "19/10/2023", 2),
        Message(3, "Nuovi turni disponibili", "21/10/2023", 1),
        Message(4, "Calendario dei turni pubblicato", "19/10/2023", 2)
    )*/

    val messageList = messageViewModel.messageList!!.map { message -> message.copy() }
    val messageDate = messageViewModel.messageList!!.map { message -> message.messageDate }

    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color),
        startY = 0f,
        endY = 2000f
    )



    Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                ,verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {

            BarraSuperiore(navController, viewModel)

            ScrittaIniziale(string = "Messages")
            Spacer(modifier = Modifier.height(100.dp))

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(boxcol)
                .padding(10.dp)
                .padding(horizontal = 20.dp, vertical = 70.dp)
                //.background(color = Color.Transparent, shape = RoundedCornerShape(10.dp))
                .scrollEnabled(enabled = true)) {
                items(messageList) { message ->
                    MessageItem(message)
                }


            }


        }


}
@Composable
fun MessageItem(message: Message) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {

            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Row(modifier = Modifier.padding(16.dp)) {
            if (message.messageType == 1)
                Image(
                    painterResource(id = R.drawable.ic_change),
                    contentDescription = "cambio turni",
                    modifier = Modifier.size(40.dp)
                )
            else
                Image(
                    painterResource(id = R.drawable.ic_calendar_foreground),
                    contentDescription = "calendario turni",
                    modifier = Modifier.size(40.dp)
                )

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)) {
                Alignment.Top
                Text(text = message.messageText)
                Text(text = message.messageDate)

            }

            /*Column(modifier = Modifier.padding(20.dp)) {
            Alignment.Top
            Text(text = message.testoMessaggio)
            Text(text = message.dataMessaggio)
        }*/

        }

    }
}

@Composable
fun TopBar(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start

        ){
            Icon(
                painter = painterResource(id =R.drawable.ic_account),
                contentDescription = "Profile",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
        Icon(
            painter = painterResource(id =R.drawable.ic_menu),
            contentDescription = "Menu",
            tint = Color.Black,
            modifier = Modifier.size(45.dp)
        )
    }
}

fun Modifier.scrollEnabled(
    enabled: Boolean,
) = nestedScroll(
    connection = object : NestedScrollConnection {
        override fun onPreScroll(
            available: Offset,
            source: NestedScrollSource
        ): Offset = if(enabled) Offset.Zero else available
    }
)

/*@Preview
@Composable
fun Preview2() {
    SchermataMessaggi(rememberNavController(), user)
}*/