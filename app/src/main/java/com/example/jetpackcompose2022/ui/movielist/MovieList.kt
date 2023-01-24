package com.example.jetpackcompose2022.ui.movielist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose2022.model.MovieSectionData

@Composable
fun MovieList(movieSectionData: List<MovieSectionData>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            movieSectionData.forEach { movieSectionData ->
                when (movieSectionData) {
                    is MovieSectionData.MovieListContent -> {
                        MovieItem(movieSectionData)
                    }
                    is MovieSectionData.MovieHeader -> {
                        Row() {
                            Text(
                                text = movieSectionData.title,
                                Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                                fontSize = 16.sp,
                                color = Color.Red
                            )
                            Text(
                                text = " - " + movieSectionData.noOfMovies,
                                Modifier.padding(start = 4.dp, top = 4.dp, bottom = 4.dp),
                                fontSize = 16.sp,
                                color = Color.Blue
                            )
                        }
                    }
                }
            }
        }
    }
}
