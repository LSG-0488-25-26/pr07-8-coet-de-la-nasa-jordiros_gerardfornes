package com.example.consumo_de_apis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.consumo_de_apis.nav.Routes
import com.example.consumo_de_apis.ui.theme.Consumo_de_apisTheme
import com.example.consumo_de_apis.view.DetailsView
import com.example.consumo_de_apis.view.MainView
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel

import kotlin.getValue

class MainActivity : ComponentActivity() {
    val consumoViewModel: ConsumoViewModel by viewModels<ConsumoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Consumo_de_apisTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Main.route
                    ) {
                        composable(Routes.Main.route) { MainView(modifier = Modifier.padding(innerPadding), consumoViewModel, navController) }
                        composable(Routes.Details.route) { DetailsView(modifier = Modifier.padding(innerPadding), consumoViewModel, navController) }
                    }
                }
            }
        }
    }
}
