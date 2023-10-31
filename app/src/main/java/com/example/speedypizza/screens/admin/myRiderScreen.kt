package com.example.speedypizza.screens.admin


import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.speedypizza.R
import com.example.speedypizza.screens.rider.BarraSuperiore
import com.example.speedypizza.screens.rider.ScrittaIniziale
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.search
import com.example.speedypizza.ui.theme.start_color


@Preview
@Composable
fun MyRiderScreen() {

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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BarraSuperiore()
            ScrittaIniziale(string = "My Rider")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp))
            MyRiderInfo()
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRiderInfo() {

    val imageUri = rememberSaveable{ mutableStateOf("") }
    val painter = rememberAsyncImagePainter(
        imageUri.value.ifEmpty{R.drawable.baseline_person_2_24}
    )

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val raiderNames= listOf("Carlo", "Matteo", "Flavio")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(boxcol)
            .padding(10.dp)


    ) {

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))

            Row (
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ){

                SearchBar(
                    colors = SearchBarDefaults.colors(Color.LightGray),
                    query = text,
                    onQueryChange ={
                        text = it
                    } ,
                    onSearch = {
                        active = false
                    },
                    active = active,
                    onActiveChange ={
                        active = it
                    },
                    placeholder = {
                        Text(text = "Search Rider")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                    },
                    trailingIcon = {
                        if(active){
                            Icon(modifier = Modifier.clickable{
                                text = ""
                            },
                                imageVector = Icons.Default.Close, contentDescription = "Close Icon")
                        }

                    }
                ) {

                }


            }




        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))

        Row(horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "My Rider:", color = search,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.lato)),
                ),
                modifier = Modifier.offset(x=10.dp)
            )
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(horizontal = 5.dp)
            ){

            items(raiderNames){ name->
                Box(){
                    Column {
                        Row {
                            Image(painter = painter,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(180.dp)
                                    .background(Color.White)
                                    .border(
                                        width = 1.dp,
                                        color = Color.White,
                                        shape = CircleShape
                                    )
                            )
                        }
                        Row {
                            Text(text = "$name")
                        }
                    }
                }
            }
        }

    }
}