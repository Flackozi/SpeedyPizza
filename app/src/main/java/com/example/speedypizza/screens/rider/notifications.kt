package com.example.speedypizza.screens.rider

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.speedypizza.R
import com.example.speedypizza.entity.Message



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
            if (message.messageType == 2)
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
        }

    }
}

