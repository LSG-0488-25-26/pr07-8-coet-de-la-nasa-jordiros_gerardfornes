package com.example.consumo_de_apis.view

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel

@Composable
fun MyBottomBar(
    consumoViewModel: ConsumoViewModel,
    navigationController: NavHostController
) {
    val bottomNavigationItems by consumoViewModel.bottomNavigationItems.observeAsState(emptyList())
    val pantalla = getWindowInfo() // OBTENER ORIENTACION PANTALLA

    if (pantalla.orientation == ScreenOrientation.PORTRAIT) {
        // ORIENTACION VERTICAL
        NavigationBar(containerColor = Color.Yellow, contentColor = Color.Black) {
            val navBackEntry by navigationController.currentBackStackEntryAsState()
            val currentRoute = navBackEntry?.destination?.route

            // GENERAR BOTONES GENERADOS EN NAVIGATION BAR
            bottomNavigationItems.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = currentRoute == item.route,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.Black,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color(0xff9000ff)
                    ),
                    onClick = {
                        if (currentRoute != item.route) {
                            navigationController.navigate(item.route)
                        }
                    }
                )
            }
        }
    } else {
        // ORIENTACION HORIZONTAL
        NavigationBar(
            modifier = Modifier.fillMaxHeight(0.20f),
            containerColor = Color.Yellow,
            contentColor = Color.Black
        ) {
            val navBackEntry by navigationController.currentBackStackEntryAsState()
            val currentRoute = navBackEntry?.destination?.route

            // GENERAR BOTONES GENERADOS EN NAVIGATION BAR
            bottomNavigationItems.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = currentRoute == item.route,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.Black,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color(0xff9000ff)
                    ),
                    onClick = {
                        if (currentRoute != item.route) {
                            navigationController.navigate(item.route)
                        }
                    }
                )
            }
        }
    }
}