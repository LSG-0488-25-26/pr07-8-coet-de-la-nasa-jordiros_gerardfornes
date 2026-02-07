package com.example.consumo_de_apis.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.consumo_de_apis.R
import com.example.consumo_de_apis.viewmodel.SearchBarViewModel

@Composable
fun HomeView(consumoViewModel: ConsumoViewModel, navController: NavController, mostrarFavoritos: Boolean, searchBarViewModel: SearchBarViewModel) {
    val personage = consumoViewModel.characters                                 // MODELO PERSONAGE
    val favoritos by consumoViewModel.favoritos.collectAsState()                // PERSONAGES FAVORITOS
    var pagina by rememberSaveable { mutableStateOf(1) }                 // NUMERO DE PAGINAS RECIBIDA DE LA API
    val gridState = rememberLazyGridState()                                     // ESTADO DEL SCROLL
    val searchText by searchBarViewModel.searchedText.observeAsState("") // TEXTO DE BUSQUEDA

    val pantalla = getWindowInfo() // OBTENER ORIENTACION PANTALLA

    // FILTRO DE BUSQUEDA DE PERSONAGES
    // SOLO FUNCIONA EN LA PAGINA DONDE SE ENCUENTRA EL USUARIO
    val personatgesFiltrats = if (searchText.isBlank()) {
        personage
    } else {
        personage.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    // ESTABLECER ESTADO SCROLL ARRIBA CUANDO CARGA VISTA
    LaunchedEffect(pagina, mostrarFavoritos) {
        gridState.scrollToItem(0)
    }

    if (pantalla.orientation == ScreenOrientation.PORTRAIT) {
        // HORIENTACION VERTICAL
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .background(Color(0xffF1F9FF))
                .fillMaxSize()
        ) {
            // IMAGEN TITULO
            Image(
                painter = painterResource(id = R.drawable.titulo),
                contentDescription = "titulo",
                modifier = Modifier.size(150.dp)
            )
            MySearchBarView(searchBarViewModel) // BARRA DE BUSQUEDA

            // TARJETA PERSONAGE CON INFORMACIÓN BÁSICA
            LazyVerticalGrid(
                state = gridState,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(20.dp, 0.dp)
                    .fillMaxHeight(0.9f)
            ) {
                // SI EL VALOR FAVORTIOS ES TRUE SOLO MOSTRARA A LOS PERSONAGES GUARDADOS COMO FAVORITOS
                // SI NO MOSTRARA A TODOS LOS PERSONAGES DE LA API
                if (!mostrarFavoritos) {
                    items(personatgesFiltrats) { personage ->
                        PersonageItem(
                            personage = personage,
                            navController = navController,
                            consumoViewModel = consumoViewModel
                        )
                    }
                } else {
                    items(favoritos) { personage ->
                        PersonageItem(
                            personage = personage,
                            navController = navController,
                            consumoViewModel = consumoViewModel
                        )
                    }
                }
            }

            // MOSTRAR BOTONES DE PAGINAS SI EL ESTADO NO ES FAVORITO
            if (!mostrarFavoritos) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    // IR A PRIMERA PAGINA
                    Button(
                        onClick = { pagina = consumoViewModel.irAPrimeraPagina() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Yellow,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.padding(end = 20.dp)
                    ) {
                        Text(text = "<<")
                    }
                    // IR A PAGINA ANTERIOR
                    Button(
                        onClick = { pagina = consumoViewModel.decrementarPagina(pagina) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Yellow,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "<")
                    }
                    // NUMERO DE PAGINA ACTUAL / NUMERO DE PAGINAS TOTALES
                    Text(
                        text = pagina.toString() + "/${consumoViewModel.maxPaginas}",
                        color = Color.Black,
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                    )
                    // IR A PAGINA SIGUIENTE
                    Button(
                        onClick = { pagina = consumoViewModel.ingrementarPagina(pagina) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Yellow,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = ">")
                    }
                    // IR A ULTIMA PAGINA
                    Button(
                        onClick = { pagina = consumoViewModel.irAUltimaPagina() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Yellow,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.padding(start = 20.dp)
                    ) {
                        Text(text = ">>")
                    }
                }
            }
        }
    } else {
        // ORIENTACION HORIZONTAL

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .background(Color(0xffF1F9FF))
                .fillMaxSize()
        ) {
            MySearchBarView(searchBarViewModel) // BARRA DE BUSQUEDA

            // TARJETA PERSONAGE CON INFORMACIÓN BÁSICA
            LazyVerticalGrid(
                state = gridState,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                columns = GridCells.Fixed(5),
                modifier = Modifier
                    .padding(20.dp, 0.dp)
                    .fillMaxHeight(0.7f)
            ) {
                // SI EL VALOR FAVORTIOS ES TRUE SOLO MOSTRARA A LOS PERSONAGES GUARDADOS COMO FAVORITOS
                // SI NO MOSTRARA A TODOS LOS PERSONAGES DE LA API
                if (!mostrarFavoritos) {
                    items(personatgesFiltrats) { personage ->
                        PersonageItem(
                            personage = personage,
                            navController = navController,
                            consumoViewModel = consumoViewModel
                        )
                    }
                } else {
                    items(favoritos) { personage ->
                        PersonageItem(
                            personage = personage,
                            navController = navController,
                            consumoViewModel = consumoViewModel
                        )
                    }
                }
            }

            // MOSTRAR BOTONES DE PAGINAS SI EL ESTADO NO ES FAVORITO
            if (!mostrarFavoritos) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    // IR A PRIMERA PAGINA
                    Button(
                        onClick = { pagina = consumoViewModel.irAPrimeraPagina() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Yellow,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.padding(end = 20.dp)
                    ) {
                        Text(text = "<<")
                    }
                    // IR A PAGINA ANTERIOR
                    Button(
                        onClick = { pagina = consumoViewModel.decrementarPagina(pagina) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Yellow,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "<")
                    }
                    // NUMERO DE PAGINA ACTUAL / NUMERO DE PAGINAS TOTALES
                    Text(
                        text = pagina.toString() + "/${consumoViewModel.maxPaginas}",
                        color = Color.Black,
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                    )
                    // IR A PAGINA SIGUIENTE
                    Button(
                        onClick = { pagina = consumoViewModel.ingrementarPagina(pagina) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Yellow,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = ">")
                    }
                    // IR A ULTIMA PAGINA
                    Button(
                        onClick = { pagina = consumoViewModel.irAUltimaPagina() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Yellow,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.padding(start = 20.dp)
                    ) {
                        Text(text = ">>")
                    }
                }
            }
        }
    }
}
