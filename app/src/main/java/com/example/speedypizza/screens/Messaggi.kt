package com.example.speedypizza.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.speedypizza.ListItem
import com.example.speedypizza.R
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color



@Preview
@Composable
fun Messages() {
    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color),
        startY = 0f,
        endY = 2000f
    )
    Box(
        modifier = Modifier
            .background(brush = gradient)
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(500.dp)
                .height(100.dp)
                .padding(20.dp)
        )
        {

            Text(
                text = "Messaggi",
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.mogra)),
                modifier = Modifier.padding(16.dp)
            )

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(500.dp)
                .height(800.dp)
                //.padding(50.dp)
                .padding(horizontal = 50.dp, vertical = 100.dp)
        )
        {
            messageList()
        }
        /*Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(500.dp)
            .height(100.dp))
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(2.dp)
        ) {
            Box(modifier = Modifier.size(300.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .width(500.dp)
                        .height(500.dp))
                {
                    messageList()
                }
            }
        }
    }*/

    }
}

@Composable
fun messageList() {

    var items by remember {
        mutableStateOf((1..20).map {
            ListItem("User $it", false)
        })
    }



    LazyColumn(modifier = Modifier
        .height(800.dp)
        .fillMaxWidth()
        .background(Color.DarkGray)) {

        items(items.size) { i ->

            Row(modifier = Modifier
                .border(width = Dp.Hairline, Color.Black, shape = RectangleShape)
                .fillMaxWidth()
                .clickable {



                    items = items.mapIndexed { j, item ->
                        if (i == j) {
                            item.copy(isSelected = !item.isSelected)
                        } else item
                    }

                }
                .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically)
            {
                Image(painterResource(R.drawable.user),"user image", modifier = Modifier.size(40.dp))
                Text(items[i].title, modifier = Modifier.background(Color.White).fillMaxWidth())
                if (items[i].isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.Green,
                        modifier = Modifier.size(20.dp)
                    )

                }
            }
        }

    }

}