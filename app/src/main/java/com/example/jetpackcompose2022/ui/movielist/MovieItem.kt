package com.example.jetpackcompose2022.ui.movielist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose2022.model.MovieData
import com.example.jetpackcompose2022.model.MovieData.MovieList

@Composable
fun MovieItem(movieData: MovieList) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        Column() {
            Text(
                text = movieData.title,
                Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                fontSize = 16.sp,
            )
            Text(
                text = movieData.title,
                Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                fontSize = 16.sp,
            )
        }
    }
}
