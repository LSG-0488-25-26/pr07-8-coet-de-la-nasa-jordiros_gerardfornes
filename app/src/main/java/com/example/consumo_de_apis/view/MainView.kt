package com.example.consumo_de_apis.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.consumo_de_apis.model.Personage
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.consumo_de_apis.nav.Routes

@Composable
fun MainView(consumoViewModel: ConsumoViewModel, navController: NavController) {
    val personage = consumoViewModel.getCharacterList()

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.background(Color(0xffF1F9FF))
    ) {
        Image(
            painter = painterResource(id = com.example.consumo_de_apis.R.drawable.titulo),
            contentDescription = "titulo",
            modifier = Modifier.size(250.dp)
        )
        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(20.dp, 0.dp)
        ) {
            items(personage) { personage ->
                PersonageItem(
                    personage,
                    navController
                )
            }
        }
    }
}

@Composable
fun PersonageItem(personage: Personage, navController: NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color(0xffc3c3c3)),
        modifier = Modifier
            .clickable {
                navController.navigate(Routes.Details.createRoute(personage.id))
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = personage.image),
                contentDescription = personage.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    color = Color.Black,
                    text = personage.name,
                    fontSize = 20.sp
                )
                Text(
                    color = Color.Gray,
                    text = personage.occupation,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp,
                    modifier = Modifier.width(120.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        color = Color.Black,
                        text = "Edad: " + personage.age,
                        fontSize = 15.sp,
                    )
                    Text(
                        color = Color.Gray,
                        text = personage.status,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}


