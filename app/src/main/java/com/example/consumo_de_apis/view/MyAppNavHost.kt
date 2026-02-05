package com.example.consumo_de_apis.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.consumo_de_apis.nav.Routes
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel

@Composable
fun MyAppNavHost(
    navController: NavController,
    consumoViewModel: ConsumoViewModel
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Routes.Main.route
    ) {
        composable(Routes.Main.route) { MainView(consumoViewModel, navController) }
        composable(Routes.Favorites.route) { FavoritesView(consumoViewModel, navController) }
        composable(
            route = Routes.Details.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            DetailsView(id, consumoViewModel)
        }
    }
}