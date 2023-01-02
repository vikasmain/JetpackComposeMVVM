package com.example.jetpackcompose2022.ui.movielist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.example.jetpackcompose2022.model.MovieData

@Composable
fun MovieList(movieData: List<MovieData>) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(movieData) { data ->
                MovieItem(data)
            }
        }
}
