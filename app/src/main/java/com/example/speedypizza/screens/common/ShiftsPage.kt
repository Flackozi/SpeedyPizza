package com.example.speedypizza.screens.common


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.speedypizza.R
import com.example.speedypizza.screens.rider.BarraSuperiore
import com.example.speedypizza.screens.rider.ScrittaIniziale
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color


//@Preview
@Composable
fun ShiftsPage(navController: NavHostController, close: () -> Unit = {}) {

    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color ),
        startY = 0f,
        endY = 2000f
    )

    ConstraintLayout {
        val (box)=createRefs()
        Box(modifier = Modifier
            .background(brush = gradient)
            .fillMaxSize()
            .constrainAs(box) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ){
            Column {
                BarraSuperiore(navController)
                ScrittaIniziale("Shifts")
                Spacer(modifier = Modifier.height(50.dp))
                ShiftsList()
            }
        }

    }

}
@Composable
fun ShiftsList() {
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
        val days =
            listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        Row(
            horizontalArrangement = Arrangement.Center, //distribuisce lo spazio tra i bottoni
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            val items =
                listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 1.dp, vertical = 10.dp)
                    .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp))
            ) {
                items(days) { day ->
                    DailyItem(day)
                }
            }
        }
    }
}
@Composable
fun DailyItem(day:String){

    val LaunchWorker= listOf("Matt", "Carlo", "Flavio")
    val DinnerWorker= listOf("Romolo", "Franco", "Sebastiano")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(110.dp)
            //.background(Color.White)
            .clickable {
                //cosa deve succedere al click
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier=Modifier.background(Color.Transparent)) {
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(25.dp)
                    .shadow(elevation = 5.dp)
                    .background(start_color),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = day,
                    color = Color.White,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.mogra)), fontSize = 13.sp)                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)){
                    Row {
                        Text(
                            text = "12:00\n15:00",
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 4.dp),
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                        LazyColumn(
                            modifier = Modifier
                                .padding(horizontal = 5.dp, vertical = 5.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            items(LaunchWorker) { name ->
                                WorkerItem(name)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.width(3.dp).background(Color.Black))

                Column(modifier = Modifier.weight(1f)){
                    Row {
                        Text(
                            text = "19:30\n22:30",
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 4.dp),
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                        LazyColumn(
                            modifier = Modifier
                                .padding(horizontal = 5.dp, vertical = 5.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            items(DinnerWorker) { name ->
                                WorkerItem(name)
                            }
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun WorkerItem(workerName: String){
    Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_work),
            contentDescription = "work",
            modifier = Modifier
                .size(18.dp),
            tint = Color.Black
        )


        Text(
            text = workerName,
            color = Color.Black,
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.width(8.dp))
    }


}

@Preview
@Composable
fun Preview5() {
    ShiftsPage(rememberNavController())
}