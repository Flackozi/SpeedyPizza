package com.example.speedypizza.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color


@Preview
@Composable
fun ShiftsPage(close: () -> Unit = {}) {

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
                BarraSuperiore()
                ScrittaIniziale("Shifts")
                ShiftsList()
            }
        }

    }

}

@Composable
fun ShiftsList() {
    Row(
        horizontalArrangement = Arrangement.Center, //distribuisce lo spazio tra i bottoni
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ){
        val items = listOf("Monday", "Tuesday", "Wednesday","Thursday","Friday", "Saturday", "Sunday")

        LazyColumn(modifier=Modifier
            .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp)),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)

        ) {

        }
    }
}

