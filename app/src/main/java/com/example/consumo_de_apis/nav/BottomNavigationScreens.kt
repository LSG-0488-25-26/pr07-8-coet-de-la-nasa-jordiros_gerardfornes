package com.example.consumo_de_apis.nav

import android.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens(
    val route: String,
    val icon: ImageVector,
    val label: String,
    val esFavorito: Boolean
){
    object Main: BottomNavigationScreens(Routes.Home.route, Icons.Filled.Home, "Main")
    object Favorites: BottomNavigationScreens(Routes.Favorites.route, Icons.Filled.Favorite, "Favorites")
}