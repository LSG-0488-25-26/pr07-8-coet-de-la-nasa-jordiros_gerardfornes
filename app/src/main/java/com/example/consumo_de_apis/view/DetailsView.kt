package com.example.consumo_de_apis.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import androidx.compose.runtime.LaunchedEffect

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsView(id: Int, viewModel: ConsumoViewModel) {
    val scrollState = rememberScrollState()

    // RETORNAR SCROLL A LA PARTE SUPERIOR
    LaunchedEffect(id) {
        viewModel.fetchCharacterDetails(id)
        scrollState.scrollTo(0)
    }

    val personage = viewModel.personageDetail // MODELO PERSONAGE

    // SI NO HAY DATOS MUESTRA UN MENSAGE DE CARGA
    if (personage == null) {
        Text("Cargando datos...", modifier = Modifier.padding(20.dp))
        return
    }

    val colorStatus = viewModel.getStatusColor(personage.status) // DETERMINA EL COLOR DE STATUS (VERDE = VIVO, ROJO = MUERTO)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF1F9FF))
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, Color(0xffc3c3c3)),
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                // IMAGEN PERSONAGE
                GlideImage(
                    model = personage.imageUrl,
                    contentDescription = personage.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(10.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // NOMBRE PERSONAGE
                    Text(
                        color = Color.Black,
                        text = personage.name,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                    )
                    // OCUPACIÓN PERSONAGE
                    Text(
                        color = Color.Gray,
                        text = personage.occupation,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp)
                    ) {
                        // EDAD PERSONAGE
                        Text(
                            color = Color.Black,
                            text = "Edad: " + personage.age,
                            fontSize = 20.sp,
                        )
                        // GENERO PERSONAGE
                        Text(
                            color = Color.Black,
                            text = "Genero: " + personage.gender,
                            fontSize = 20.sp,
                        )
                        // CUMPLEAÑOS PERSONAGE
                        Text(
                            color = Color.Black,
                            text = "Cumpleaños: " + personage.birthday,
                            fontSize = 20.sp,
                        )
                        Row {
                            // ESTADO PERSONAGE (VIVO O MUERTO)
                            Text(
                                color = Color.Black,
                                text = "Estado: ",
                                fontSize = 20.sp,
                            )
                            Text(
                                color = Color(colorStatus),
                                text = personage.status,
                                fontSize = 20.sp,
                            )
                        }
                        // DESCRIPCIÓN PERSONAGE
                        Text(
                            color = Color.Black,
                            text = "Descripción: " + personage.description,
                            fontSize = 20.sp,
                        )
                    }
                }
            }
        }
    }
}