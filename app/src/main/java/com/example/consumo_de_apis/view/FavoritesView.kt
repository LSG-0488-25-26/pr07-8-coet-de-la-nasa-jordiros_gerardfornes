package com.example.consumo_de_apis.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun FavoritesView(consumoViewModel: ConsumoViewModel, navController: NavController) {
    val favoritos by consumoViewModel.favoritos.collectAsState()

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(20.dp, 0.dp)
            .height(450.dp)
    ) {
        items(favoritos) { personage ->
            PersonageItem(
                personage = personage,
                navController = navController,
                consumoViewModel = consumoViewModel
            )
        }
    }
}