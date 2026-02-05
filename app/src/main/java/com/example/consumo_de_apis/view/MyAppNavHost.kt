package com.example.consumo_de_apis.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.consumo_de_apis.nav.Routes
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel
import com.example.consumo_de_apis.viewmodel.SearchBarViewModel
import okhttp3.Route

@Composable
fun MyAppNavHost(navController: NavController, consumoViewModel: ConsumoViewModel, searchBarViewModel: SearchBarViewModel) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) { HomeView(consumoViewModel, navController, false, searchBarViewModel) }
        composable(Routes.Favorites.route) { FavoritesView(consumoViewModel, navController, searchBarViewModel) }
        composable(Routes.Main.route) { MainView(consumoViewModel, navController, searchBarViewModel) }
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