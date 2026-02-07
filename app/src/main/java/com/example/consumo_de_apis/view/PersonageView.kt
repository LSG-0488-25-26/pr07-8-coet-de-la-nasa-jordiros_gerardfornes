package com.example.consumo_de_apis.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.consumo_de_apis.R
import com.example.consumo_de_apis.model.Personage
import com.example.consumo_de_apis.nav.Routes
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel

// TARJETA PERSONAGE MOSTRADA EN LISTA
@OptIn(ExperimentalGlideComposeApi::class) // NECESARIO PARA UTILIZAR GLIDEIMAGE
@Composable
fun PersonageItem(
    personage: Personage,
    navController: NavController,
    consumoViewModel: ConsumoViewModel
) {
    val colorStatus = consumoViewModel.getStatusColor(personage.status) // COLOR DEL ESTADO DE PERSONAGE

    val pantalla = getWindowInfo() // OBTENER ORIENTACION PANTALLA

    if (pantalla.orientation == ScreenOrientation.PORTRAIT) {
        // HORIENTACION VERTICAL
        // TARJETA PERSONAGE
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
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .height(375.dp)
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(10.dp)
            ) {
                // BOTON PARA ALTERAR FAVORITO
                Button(
                    onClick = {
                        consumoViewModel.cambiarFavorito(personage)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Transparent
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    // IMAGEN ESTRELLA (FAVORTIO = AMARILLO, NO FAVORITO = GRIS TRANSPARENTE)
                    Image(
                        painter = painterResource(
                            if (personage.esFavorito)
                                R.drawable.favorito_on
                            else
                                R.drawable.favorito_off
                        ),
                        contentDescription = "Favorito",
                        modifier = Modifier.size(30.dp)
                    )
                }
                // IMAGEN PERSONAGE
                GlideImage(
                    model = personage.imageUrl,
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
                    // NOMBRE PERSONAGE
                    Text(
                        color = Color.Black,
                        text = personage.name,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                    )
                    // OCUPACON PERSONAGE
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
                        // EDAD PERSONAGE
                        Text(
                            color = Color.Black,
                            text = "Edad: " + personage.age,
                            fontSize = 15.sp,
                        )
                        // ESTADO PESONAGE (VIVO O MUERTO)
                        Text(
                            color = Color(colorStatus),
                            text = personage.status,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    } else {
        // HORIENTACION HORIZONTAL
        // TARJETA PERSONAGE
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
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .height(330.dp)
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(10.dp)
            ) {
                // BOTON PARA ALTERAR FAVORITO
                Button(
                    onClick = {
                        consumoViewModel.cambiarFavorito(personage)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Transparent
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    // IMAGEN ESTRELLA (FAVORTIO = AMARILLO, NO FAVORITO = GRIS TRANSPARENTE)
                    Image(
                        painter = painterResource(
                            if (personage.esFavorito)
                                R.drawable.favorito_on
                            else
                                R.drawable.favorito_off
                        ),
                        contentDescription = "Favorito",
                        modifier = Modifier.size(30.dp)
                    )
                }

                if (pantalla.width >= 1080.dp) {
                    // IMAGEN PERSONAGE
                    GlideImage(
                        model = personage.imageUrl,
                        contentDescription = personage.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .padding(10.dp)
                    )
                } else {
                    // IMAGEN PERSONAGE
                    GlideImage(
                        model = personage.imageUrl,
                        contentDescription = personage.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // NOMBRE PERSONAGE
                    Text(
                        color = Color.Black,
                        text = personage.name,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                    )
                    // OCUPACON PERSONAGE
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
                        // EDAD PERSONAGE
                        Text(
                            color = Color.Black,
                            text = "Edad: " + personage.age,
                            fontSize = 15.sp,
                        )
                        // ESTADO PESONAGE (VIVO O MUERTO)
                        Text(
                            color = Color(colorStatus),
                            text = personage.status,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}