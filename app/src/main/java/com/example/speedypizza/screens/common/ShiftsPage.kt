package com.example.speedypizza.screens.common


import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.speedypizza.R
import com.example.speedypizza.entity.Days
import com.example.speedypizza.entity.Shifts
import com.example.speedypizza.screens.rider.BarraSuperiore
import com.example.speedypizza.screens.rider.ScrittaIniziale
import com.example.speedypizza.screens.viewmodel.LoginViewModel
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.start_color



@Composable
fun ShiftsPage(
    navController: NavHostController,
    viewModel: LoginViewModel,
    allShifts: List<Shifts>?,
    daysInfo: List<Days>?
) {


    ConstraintLayout {
        val (box)=createRefs()
        Box(modifier = Modifier
            .background(start_color)
            .fillMaxSize()
            .constrainAs(box) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ){
            Column {
                BarraSuperiore(navController, viewModel)
                ScrittaIniziale("Shifts")
                Spacer(modifier = Modifier.height(40.dp))
                ShiftsList(allShifts, daysInfo)
            }
        }

    }

}


@Composable
fun ShiftsList(allShifts: List<Shifts>?, daysInfo: List<Days>?) {


    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")


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


        Spacer(modifier = Modifier.height(93.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(4.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 1.dp, vertical = 10.dp)
                    .fillMaxHeight().fillMaxWidth()
                    .background(
                        color = Color.Transparent, shape = RoundedCornerShape(10.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {

                itemsIndexed(days.chunked(2)) { index, towDays ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(), horizontalArrangement = Arrangement.spacedBy(10.dp)){
                        towDays.forEachIndexed(){ dayIndex, day->


                            val shiftsForDay = allShifts?.filter { it.day == day }
                            val min = daysInfo?.find{it.day == day}?.min
                            val max = daysInfo?.find{it.day == day}?.max

                            if (day != null) {
                                if (day == "Sunday" && dayIndex == towDays.lastIndex && days.size % 2 != 0) {

                                    Box(
                                        modifier = Modifier
                                            .width(200.dp).offset(x=93.dp)
                                    ) {
                                        DailyItem(day, shiftsForDay, min, max)
                                    }
                                } else {

                                    DailyItem(day, shiftsForDay, min, max)


                                }
                            }
                        }
                    }

                }


            }
        }
    }
}
@Composable
fun DailyItem(day: String, riders: List<Shifts>?, min: Int?, max: Int?){



    Card(
        modifier = Modifier
            .width(170.dp)
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
                    .fillMaxWidth()
                    .height(25.dp)
                    .shadow(elevation = 5.dp)
                    .background(start_color),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = day,
                    color = Color.White,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.mogra)), fontSize = 17.sp)
                )
            }
            if (riders != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {

                        Text(text = "min: $min, max: $max")

                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp),
                            color = Color.DarkGray,
                            thickness = 1.dp
                        )

                        LazyColumn(
                            modifier = Modifier
                                .padding(horizontal = 5.dp, vertical = 5.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            items(riders) { shift ->
                                WorkerItem(shift.rider)
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
