package com.example.jetpackcompose2022.ui.movielist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose2022.model.MovieResponse
import com.example.jetpackcompose2022.model.MovieSectionData
import com.example.jetpackcompose2022.viewmodel.MovieScreenState

@Composable
fun MovieList(movieSectionData: List<MovieSectionData>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            movieSectionData.forEach {
                when (it) {
                    is MovieSectionData.MovieListContent -> {
                        MovieItem(data)
                    }
                    is MovieSectionData.MovieHeader -> {
                        Column() {
                            Text(
                                text = it.movieData.title,
                                Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                                fontSize = 16.sp,
                            )
                            Text(
                                text = movieData.description,
                                Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                                fontSize = 16.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}
