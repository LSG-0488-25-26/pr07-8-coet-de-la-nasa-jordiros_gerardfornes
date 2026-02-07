package com.example.consumo_de_apis.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel
import com.example.consumo_de_apis.viewmodel.SearchBarViewModel

@Composable
fun FavoritesView(consumoViewModel: ConsumoViewModel, navController: NavController, searchBarViewModel: SearchBarViewModel) {
    HomeView(consumoViewModel, navController, true, searchBarViewModel) // LLAMA A LA HOME PARA VER PERSONAGES FAVORITOS
}