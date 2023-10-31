package com.example.speedypizza.screens.common

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.speedypizza.R
import com.example.speedypizza.ui.theme.arrowProfile
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color



@Composable
fun ProfileScreen(navController: NavHostController) {


    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color),
        startY = 0f,
        endY = 2000f
    )
    val imageUri = rememberSaveable{ mutableStateOf("") }
    val painter = rememberAsyncImagePainter(
        imageUri.value.ifEmpty{R.drawable.baseline_person_2_24}
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){
        uri: Uri? ->
        uri?.let{imageUri.value = it.toString()}
    }


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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BarraSuperioreProfile()

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

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))

                Text(text = "Matteo Francobollini",
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.White,
                    fontWeight = FontWeight.Bold

                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp))

                Text(text = "Rider",
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.White,

                )
            }

            Box(
                modifier = Modifier.offset(x=230.dp, y=200.dp)
                //.padding(top = 280.dp, start = 260.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Black)
                        .size(40.dp)
                        .padding(10.dp)
                        .clickable { launcher.launch("image/*") }
                )
            }
            Row (modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(500.dp)){
                ProfileInfo()
            }

        }
    }
}

@Composable
fun ProfileInfo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(boxcol)
            .padding(10.dp)

    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 30.dp),
            contentAlignment = Alignment.TopCenter


        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier
                    .width(330.dp)
                    .height(60.dp)
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(15.dp)
                    ),
                    contentAlignment = Alignment.CenterStart
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_lock_open_24),
                        contentDescription = "Lock Open",
                        modifier = Modifier
                            .size(30.dp)
                            .offset(x = 15.dp),
                        tint = start_color
                    )

                    Text(text = "Password",
                        modifier = Modifier.offset(x=60.dp),
                        fontSize = 18.sp,
                        color = arrowProfile,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = "arrow",
                        modifier = Modifier
                            .size(45.dp)
                            .offset(x = 275.dp),
                        tint = arrowProfile
                    )



                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp))

                Box(modifier = Modifier
                    .width(330.dp)
                    .height(60.dp)
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(15.dp)
                    ),
                    contentAlignment = Alignment.CenterStart
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_mail_outline_24),
                        contentDescription = "Mail",
                        modifier = Modifier
                            .size(30.dp)
                            .offset(x = 15.dp),
                        tint = start_color
                    )

                    Text(text = "justointer@gmail.com",
                        modifier = Modifier.offset(x=60.dp),
                        fontSize = 18.sp,
                        color = arrowProfile,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = "arrow",
                        modifier = Modifier
                            .size(45.dp)
                            .offset(x = 275.dp),
                        tint = arrowProfile
                    )
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp))

                Box(modifier = Modifier
                    .width(330.dp)
                    .height(60.dp)
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(15.dp)
                    ),
                    contentAlignment = Alignment.CenterStart
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_contact_phone_24),
                        contentDescription = "Phone",
                        modifier = Modifier
                            .size(30.dp)
                            .offset(x = 15.dp),
                        tint = start_color
                    )

                    Text(text = "3490705950",
                        modifier = Modifier.offset(x=60.dp),
                        fontSize = 18.sp,
                        color = arrowProfile,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = "arrow",
                        modifier = Modifier
                            .size(45.dp)
                            .offset(x = 275.dp),
                        tint = arrowProfile
                    )
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp))

                Box(modifier = Modifier
                    .width(330.dp)
                    .height(60.dp)
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(15.dp)
                    ),
                    contentAlignment = Alignment.CenterStart
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_clear_24),
                        contentDescription = "Logout",
                        modifier = Modifier
                            .size(30.dp)
                            .offset(x = 15.dp),
                        tint = start_color
                    )

                    Text(text = "Sign Out",
                        modifier = Modifier.offset(x=60.dp),
                        fontSize = 18.sp,
                        color = arrowProfile,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = "arrow",
                        modifier = Modifier
                            .size(45.dp)
                            .offset(x = 275.dp),
                        tint = arrowProfile
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BarraSuperioreProfile(){



    TopAppBar(title = {
        Text(text = "")
    },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent ),
        navigationIcon = {


            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                //icona per tornare in dietro
                Icon(
                    painter = painterResource(id = R.drawable.baseline_keyboard_backspace_24),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(40.dp),
                    tint = Color.White

                )


            }


        }
    )

}
@Preview
@Composable
fun Preview() {
    ProfileScreen(rememberNavController())
}