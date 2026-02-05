package com.example.consumo_de_apis.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun HomeView(consumoViewModel: ConsumoViewModel, navController: NavController) {
    val personage = consumoViewModel.characters
    val favoritos by consumoViewModel.favoritos.collectAsState()

    var pagina by rememberSaveable { mutableStateOf(1) }
    var opcion_ventana by rememberSaveable { mutableStateOf(true) } // true: ventana personages || true: ventana favoritos
    var color_personages = consumoViewModel.cambiarColor(opcion_ventana)
    var color_favorito = consumoViewModel.cambiarColor(!opcion_ventana)

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
        Row (
            modifier = Modifier.padding(8.dp)
        ) {
            Button(
                onClick = {
                    opcion_ventana = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(color_personages),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Personages")
            }
            Spacer(Modifier.padding(20.dp))
            Button(
                onClick = {
                    opcion_ventana = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(color_favorito),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Favoritos")
            }
        }

        // LAZY COMPONENTS

        if (opcion_ventana) {
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
