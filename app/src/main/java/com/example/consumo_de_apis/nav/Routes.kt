package com.example.consumo_de_apis.nav

// ESTABLECER RUTAS A VISTAS
sealed class Routes(val route: String) {
    object Home : Routes("homeview")
    object Main : Routes("mainview")
    object Favorites: Routes("favoritos")
    object Details : Routes("details/{id}") {
        fun createRoute(id: Int): String {
            return "details/$id"
        }
    }
}