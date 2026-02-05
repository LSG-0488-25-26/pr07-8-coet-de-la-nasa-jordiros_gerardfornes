package com.example.consumo_de_apis.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel

@Composable
fun ScaffoldView(consumoViewModel: ConsumoViewModel, navController: NavHostController){
    Scaffold (
        bottomBar = { MyBottomBar(consumoViewModel, navController) }
    )
    { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            MyAppNavHost(navController, consumoViewModel)
        }
    }
}