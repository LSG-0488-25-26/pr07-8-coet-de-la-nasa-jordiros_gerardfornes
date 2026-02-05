package com.example.consumo_de_apis.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.consumo_de_apis.R
import com.example.consumo_de_apis.nav.BottomNavigationScreens

@Composable
fun HomeView(consumoViewModel: ConsumoViewModel, navController: NavController, mostrarFavoritos: Boolean) {
    val personage = consumoViewModel.characters
    val favoritos by consumoViewModel.favoritos.collectAsState()

    var pagina by rememberSaveable { mutableStateOf(1) }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .background(Color(0xffF1F9FF))
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.titulo),
            contentDescription = "titulo",
            modifier = Modifier.size(150.dp)
        )

        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(20.dp, 0.dp)
                .height(520.dp)
        ) {
            if(!mostrarFavoritos) {
                items(personage) { personage ->
                    PersonageItem(
                        personage = personage,
                        navController = navController,
                        consumoViewModel = consumoViewModel
                    )
                }
            } else {
                items(favoritos) { personage ->
                    PersonageItem(
                        personage = personage,
                        navController = navController,
                        consumoViewModel = consumoViewModel
                    )
                }
            }
        }

        if (!mostrarFavoritos) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Button(
                    onClick = { pagina = consumoViewModel.irAPrimeraPagina() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.padding(end = 20.dp)
                ) {
                    Text(text = "<<")
                }
                Button(
                    onClick = { pagina = consumoViewModel.decrementarPagina(pagina) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "<")
                }
                Text(
                    text = pagina.toString() + "/${consumoViewModel.maxPaginas}",
                    color = Color.Black,
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                )
                Button(
                    onClick = { pagina = consumoViewModel.ingrementarPagina(pagina) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = ">")
                }
                Button(
                    onClick = { pagina = consumoViewModel.irAUltimaPagina() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Text(text = ">>")
                }
            }
        }
    }
}
