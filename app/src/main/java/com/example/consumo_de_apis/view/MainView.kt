package com.example.consumo_de_apis.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.consumo_de_apis.model.Personage
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel

@Composable
fun MainView(modifier: Modifier, consumoViewModel: ConsumoViewModel, navController: NavController) {
    val characters = consumoViewModel.getCharacterList() // obtiene la lista de personajes

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .padding(vertical = 30.dp)
            .fillMaxHeight()
    ) {
        items(characters) { personage ->
            PersonageItem(personage = personage)
        }
    }

}

@Composable
fun PersonageItem(personage: Personage) {
    Card(border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row {
            Image(
                painter = painterResource(id = personage.image),
                contentDescription = personage.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(220.dp)
                    .padding(8.dp)
            )
            Column{
                Text(
                    text = "Nombre: " + personage.name,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Edad: " + personage.age.toString() + " años")
            }
        }
    }
}