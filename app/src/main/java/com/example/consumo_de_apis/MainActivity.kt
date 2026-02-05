package com.example.consumo_de_apis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.consumo_de_apis.ui.theme.Consumo_de_apisTheme
import com.example.consumo_de_apis.view.ScaffoldView
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel
import com.example.consumo_de_apis.viewmodel.SearchBarViewModel

// ACTIVIDAD PRINCIPAL DE LA APLICACION
class MainActivity : ComponentActivity() {
    val consumoViewModel: ConsumoViewModel by viewModels<ConsumoViewModel>() // VIEWMODEL PRINCIPAL PARA CONSUMO DE API Y LOGICA GENERAL

    override fun onCreate(savedInstanceState: Bundle?) {
        val searchBarViewModel by viewModels<SearchBarViewModel>()// VIEWMODEL PARA LA SEARCH BAR

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Consumo_de_apisTheme {
                val navController = rememberNavController() // CONTROLADOR DE NAVEGACION ENTRE PANTALLAS

                // VIEW PRINCIPAL QUE CONTIENE EL SCAFFOLD Y LA NAVEGACION
                ScaffoldView(
                    consumoViewModel,
                    navController,
                    searchBarViewModel
                )
            }
        }
    }
}
