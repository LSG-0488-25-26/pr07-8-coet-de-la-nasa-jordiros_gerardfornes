package com.example.lazycomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.consumo_de_apis.ui.theme.Consumo_de_apisTheme
import com.example.consumo_de_apis.view.MainView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Consumo_de_apisTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView (
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}