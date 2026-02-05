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

@OptIn(ExperimentalGlideComposeApi::class) // Necessari per utilitzar GlideImage
@Composable
fun PersonageItem(
    personage: Personage,
    navController: NavController,
    consumoViewModel: ConsumoViewModel
) {
    val colorStatus = consumoViewModel.getStatusColor(personage.status)
    var imagen_favorito =
        if (personage.esFavorito) { R.drawable.favorito_on }
        else { R.drawable.favorito_off }

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
                Image(
                    painter = painterResource(id = imagen_favorito),
                    contentDescription = "Favorito",
                    modifier = Modifier.size(30.dp)
                )
            }
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
                Text(
                    color = Color.Black,
                    text = personage.name,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
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
                        color = Color(colorStatus),
                        text = personage.status,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}