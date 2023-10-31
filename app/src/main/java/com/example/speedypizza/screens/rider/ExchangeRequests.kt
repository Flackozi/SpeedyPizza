package com.example.speedypizza.screens.rider

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.speedypizza.R
import com.example.speedypizza.ui.theme.boxcol
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.green2
import com.example.speedypizza.ui.theme.grigiochiarissimo
import com.example.speedypizza.ui.theme.start_color

@Preview
@Composable
fun ExchangeRequests(close: () -> Unit = {}) {

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
                ScrittaIniziale("Exchange Requests")
                Spacer(modifier = Modifier.height(50.dp))
                RequestsList()
            }
        }

    }

}

@Composable
fun RequestsList(){
    val raiderNames= listOf("Carlo", "Matteo", "Flavio")

    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf("Seleziona un'opzione")
    }

    val options= listOf("Opzione1", "Opzione 2", "Opzione 3")

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
        Box(
            modifier=Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                contentPadding = PaddingValues(horizontal = 5.dp),
            ) {
                items(raiderNames) { name ->
                    RichiesteItem(name)
                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
        }
        Column(modifier=Modifier.fillMaxHeight()){
            DropDown(text = "Carlo", modifier = Modifier.padding(15.dp)) {
                Text(
                    text = "Giustini Gay", modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(grigiochiarissimo)
                )
            }
        }
    }
}

@Composable
fun RichiesteItem(string: String){
    Card(
        modifier= Modifier
            .height(130.dp)
            .width(150.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(3.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(3.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_account),
                        contentDescription = "work",
                        modifier = Modifier
                            .size(60.dp),
                        tint = Color.Black
                    )
                    Text(text=string, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier= Modifier
                        .width(25.dp)
                        .height(25.dp),
                ) {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .background(green2),
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
                Box(
                    modifier= Modifier
                        .width(25.dp)
                        .height(25.dp),

                ) {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .background(center_color),
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.Black
                    )

                }

            }
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Monday: 12:00/15:00",
                color = Color.Black,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold)
            )

            Text(
                text = "Friday: 12:00/15:00",
                color = Color.Black,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold)
            )

        }
    }


}

@Composable
fun DropDown(
    text: String,
    modifier: Modifier = Modifier,
    initiallyOpened: Boolean = false,
    content: @Composable () -> Unit
) {
    var isOpen by remember {
        mutableStateOf(initiallyOpened)
    }

    val alpha= animateFloatAsState(
        targetValue = if(isOpen) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300
        )
    )

    val rotateX = animateFloatAsState(
        targetValue = if(isOpen) 0f else -90f,
        animationSpec = tween(
            durationMillis = 300
        )
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(9.dp))
            .shadow(elevation=3.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(center_color)

        ) {
            Text(text="")
            Text(
                //modifier=Modifier.padding(start=80.dp),
                text = text,
                color = Color.White,
                fontSize = 20.sp,
                style= TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open or close the drop down",
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        isOpen = !isOpen
                    }
                    .scale(1f, if (isOpen) -1f else 1f)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    transformOrigin = TransformOrigin(0.5f, 0f)
                    rotationX = rotateX.value
                }
                .alpha(alpha.value)
        ) {
            content()
        }
    }
}