package com.example.consumo_de_apis.nav

sealed class Routes(val route: String) {
    object Main : Routes("mainview")
    object Details : Routes("details/{id}") {
        fun createRoute(id: Int): String {
            return "details/$id"
        }
    }
}
