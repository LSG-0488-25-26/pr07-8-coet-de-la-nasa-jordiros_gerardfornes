package com.example.consumo_de_apis.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel

@Composable
fun MainView(consumoViewModel: ConsumoViewModel, navController: NavController) {
    HomeView(consumoViewModel, navController, false)
}
