package com.example.consumo_de_apis.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel

@Composable
fun FavoritesView(consumoViewModel: ConsumoViewModel, navController: NavController) {
    HomeView(consumoViewModel, navController, true)
}