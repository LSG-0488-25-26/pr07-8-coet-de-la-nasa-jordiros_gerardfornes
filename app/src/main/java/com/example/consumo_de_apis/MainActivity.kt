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

class MainActivity : ComponentActivity() {
    val consumoViewModel: ConsumoViewModel by viewModels<ConsumoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Consumo_de_apisTheme {
                val navigationController = rememberNavController()
                ScaffoldView(consumoViewModel, navigationController)
            }
        }
    }
}