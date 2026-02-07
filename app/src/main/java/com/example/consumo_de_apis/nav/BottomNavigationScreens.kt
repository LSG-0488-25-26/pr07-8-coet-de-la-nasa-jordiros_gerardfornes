package com.example.consumo_de_apis.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens(
    val route: String,
    val icon: ImageVector,
    val label: String
){
    object Main: BottomNavigationScreens(Routes.Main.route, Icons.Filled.Home, "Main") // BOTON DE BOTTOMBAR PARA CARGAR MAIN
    object Favorites: BottomNavigationScreens(Routes.Favorites.route, Icons.Filled.Favorite, "Favorites") // BOTTON DE BOTTOMBAR PARA CARGAR FAVORITOS
}