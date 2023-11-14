package com.example.speedypizza.screens.common

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.speedypizza.R
import com.example.speedypizza.db.Repository
import com.example.speedypizza.entity.User
import com.example.speedypizza.db.UserDatabase
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color
import com.example.speedypizza.ui.theme.whitebackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun LoginPage(navController: NavHostController, viewModel: LoginViewModel) {



    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color ),
        startY = 0f,
        endY = 2000f
    )
    val buttonColor = ButtonDefaults.buttonColors(start_color)

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    var em: Int
    val user: MutableState<User?> = rememberSaveable { mutableStateOf(null) }

    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val db = UserDatabase.getInstance(context)
    val repository = Repository(db.userDao())




    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

            val image = createRef()
            Box(modifier = Modifier
                .fillMaxSize()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .background(brush = gradient), contentAlignment = Alignment.TopCenter){

                Image(painterResource(R.drawable.pizza),"content description", modifier = Modifier
                    .offset(y = 145.dp)
                    .size(170.dp))
            }
        }


        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()

        ){

            val (col1, col2) = createRefs()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(col1) {
                        top.linkTo(parent.top)
                    },
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {




                Image(painterResource(R.drawable.pizzaslice),"content description", contentScale = ContentScale.Fit, modifier = Modifier
                    .offset(y = 14.dp)
                    .size(71.dp)
                    .rotate(300f),
                    colorFilter = ColorFilter.tint(Color.White))

                Text(
                    text = "SpeedyPizza",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.mogra)), // Sostituisci con il tuo tipo di carattere
                        fontSize = 35.sp,
                        color = Color.White // Sostituisci con il tuo colore
                    ),
                    modifier = Modifier
                        .padding(0.dp)
                        .offset(y = 5.dp))




            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(col2) {
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxHeight(0.60f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(whitebackground)
                    .padding(10.dp)
            ) {
                Text(
                    text = stringResource(R.string.Sign_In),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    ),
                    fontSize = 40.sp
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = emailValue.value,
                        onValueChange = { emailValue.value = it},
                        label = { Text(text = stringResource(R.string.Email_Address))},
                        placeholder = { Text(text = stringResource(R.string.Email_Address))},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    OutlinedTextField(
                        value = passwordValue.value,
                        onValueChange = { passwordValue.value = it},

                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.password_eye),
                                    contentDescription = null,
                                    tint = if (passwordVisibility.value) Color.Black else Color.Gray
                                )
                            }
                        },
                        label = { Text(text = "Password")},
                        placeholder = { Text(text = "Password")},
                        singleLine = true,
                        visualTransformation = if(passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = {


                            CoroutineScope(Dispatchers.Main).launch {
                                val user = viewModel.login(emailValue.value, passwordValue.value).await()


                                if (user.role==1) {
                                    navController.navigate("riderHome")
                                } else if (user.role == 2){
                                    navController.navigate("adminHome")
                                }
                            }


                        },
                        colors = buttonColor,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)

                    ) {
                        Text(text= stringResource(id = R.string.Sign_In), fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(
                        text= stringResource(R.string.Create_An_Account),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.clickable(onClick = {})
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                }

            }
        }




    }

}

@Preview
@Composable
fun GradientBackgroundPreview() {
    LoginPage(rememberNavController(), viewModel())
}


fun saveAccess(
    context: Context,
    user: User?
){
    val sharedPreferences = context.getSharedPreferences("Profile", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    editor.putString("Email", user?.email)
    editor.putString("Password", user?.password)

    editor.apply()
}
