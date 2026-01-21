package com.example.consumo_de_apis.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.consumo_de_apis.viewmodel.ConsumoViewModel

@Composable
fun DetailsView(id: Int, viewModel: ConsumoViewModel) {
    val character = viewModel.getCharacterById(id)
    character?.let {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = it.image),
                contentDescription = it.name,
                modifier = Modifier.size(250.dp)
            )

            Text(it.name, fontSize = 24.sp)
            Text(it.occupation)
            Text(it.status)
            Text(it.description)
        }
    }
}
