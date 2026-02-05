package com.example.consumo_de_apis.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.consumo_de_apis.viewmodel.SearchBarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBarView(myViewModel: SearchBarViewModel) {
    val searchedText by myViewModel.searchedText.observeAsState("")
    val searchHistory by myViewModel.searchHistory.observeAsState(emptyList())
    SearchBar(
        query = searchedText,
        onQueryChange = { myViewModel.onSearchTextChange(it) },
        onSearch = { myViewModel.addToHistory(it) },
        active = false,
        onActiveChange = { },
        colors = SearchBarDefaults.colors(
            containerColor = Color.Yellow,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = Color.Black
            )
        },
        trailingIcon = {
            if (searchHistory.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear",
                    tint = Color.Red,
                    modifier = Modifier.clickable { myViewModel.clearHistory() }
                )
            }
        },
        placeholder = {
            Text(
                "What are you looking for?",
                color = Color.DarkGray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {}
}