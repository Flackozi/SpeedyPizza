package com.example.speedypizza.screens.admin


import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.speedypizza.R
import com.example.speedypizza.entity.User
import com.example.speedypizza.screens.rider.BarraSuperiore
import com.example.speedypizza.screens.rider.ScrittaIniziale
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.search
import com.example.speedypizza.ui.theme.start_color


@Composable
fun MyRiderScreen(navController: NavHostController, viewModel: LoginViewModel) {

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
            BarraSuperiore(navController, viewModel)
            ScrittaIniziale(string = "My Rider")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp))
            MyRiderInfo(viewModel, navController)
        }
    }
}




@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRiderInfo(viewModel: LoginViewModel, navController: NavHostController) {


    var popupControl by remember { mutableStateOf(false) }

    val imageUri = rememberSaveable{ mutableStateOf("") }
    val painter = rememberAsyncImagePainter(
        imageUri.value.ifEmpty{R.drawable.baseline_person_2_24}
    )

    val nicknamesList = viewModel.myRiders!!.map { user -> user.nickname }
    var rider: User? = null



    //lista oggetti cercati di recente
    val recentItems = remember { mutableStateListOf("francesco03") }

    //val myRidersDeferred: Deferred<List<User>> = viewModel.retrieveMyRider()
    //val myRiders: List<User> = myRidersDeferred.await()



    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    //val riderNames= listOf("Carlo", "Matteo", "Flavio", "Mirko", "aghislov", "becknabour", "franco", "armando")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
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

                         val filteredList = nicknamesList.filter { nickname ->
                            nickname.contains(text, ignoreCase = true)
                        }



                        if (filteredList.isNotEmpty()) {
                             popupControl = true

                        }
                        recentItems.add(text)

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
                                active = false
                            },
                                imageVector = Icons.Default.Close, contentDescription = "Close Icon"
                            )
                        }

                    }
                ) {

                    recentItems.forEach{
                        Row(modifier = Modifier.padding(all = 14.dp)) {
                            Icon(
                                modifier = Modifier.padding(all = 10.dp),
                                painter = painterResource(id = R.drawable.baseline_history_24),
                                contentDescription = "history"
                            )

                            Text(modifier = Modifier.align(Alignment.CenterVertically),text = it)
                        }
                    }
                }


            }

        if(popupControl){
            //popup
            Column(modifier = Modifier
                .background(boxcol)
                .width(350.dp)
                .height(200.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Popup(alignment = Alignment.Center, onDismissRequest = { popupControl = false },
                    properties = PopupProperties(focusable = true)
                ) {


                    Box( modifier = Modifier
                        .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
                        .width(350.dp)
                        .height(200.dp)
                        .padding(all = 14.dp)
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,) {

                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp))




                            Column(modifier = Modifier.fillMaxWidth()) {

                                Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(all = 5.dp)) {

                                    Text(text = "Phone: ")

                                    rider = viewModel.myRiders!!.find { it.nickname == text }

                                    Text(text = rider!!.phone )

                                }
                                Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(all = 5.dp)) {
                                    Text(text = "Email: ")
                                    Text(text = rider!!.email)
                                }
                            }




                            Spacer(modifier = Modifier
                                .height(100.dp))

                            Row {

                                Button(
                                    onClick = {popupControl = false},
                                    colors = ButtonDefaults.buttonColors(start_color),
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(40.dp)

                                ) {
                                    Text(text = "Delete Rider")
                                }
                                Spacer(modifier = Modifier
                                    .width(30.dp))

                                Button(
                                    onClick = {popupControl = false},
                                    colors = ButtonDefaults.buttonColors(Color.Black),
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(40.dp)

                                ) {
                                    Text(text = "Add Rider")
                                }

                            }

                        }
                    }


                }
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

        //item myrider
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.Start,
            contentPadding = PaddingValues(horizontal = 20.dp)
            ){

            items(nicknamesList)
            { name->
                Box(){
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top) {
                        Row {
                            Image(painter = painter,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(50.dp)
                                    .background(Color.LightGray)
                                    .border(
                                        width = 1.dp,
                                        color = Color.LightGray,
                                        shape = CircleShape
                                    )
                            )
                        }
                        Row {
                            Text(text = "$name")
                        }
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }


        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

    }


    Column(
        //horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.background(boxcol)
    ) {
        Row(horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Rider:", color = search,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.lato)),
                ),
                modifier = Modifier.offset(x=20.dp)
            )

            Spacer(modifier = Modifier
                .width(280.dp))


        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))

        //add rider
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.offset(x=45.dp)){
                IconButton(
                    onClick = { /*aggiungi rider*/ },
                    //shape = CircleShape,
                    modifier = Modifier.background(color = Color.LightGray, shape = MaterialTheme.shapes.extraLarge)
                    //colors = ButtonDefaults.buttonColors(start_color)
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        tint = start_color
                    )
                }
            }

            Spacer(modifier = Modifier
                .width(50.dp))

            Text(text = "Add new rider", color = start_color,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.lato)),
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
                )
        }

        //rider che conosco
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 5.dp)
                .offset(x = 20.dp)
                .fillMaxHeight(),
            contentPadding = PaddingValues(horizontal = 20.dp),
        ){



            items(nicknamesList){ name->
                Box(){
                        Row(horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(painter = painter,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(50.dp)
                                    .background(Color.LightGray)
                                    .border(
                                        width = 1.dp,
                                        color = Color.LightGray,
                                        shape = CircleShape
                                    )
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "$name")
                        }




                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }







}