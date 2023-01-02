package com.example.jetpackcompose2022.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.jetpackcompose2022.deps.DaggerMovieComponent
import com.example.jetpackcompose2022.model.MovieData
import com.example.jetpackcompose2022.ui.movielist.MovieList
import com.example.jetpackcompose2022.ui.theme.Jetpackcompose2022Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = DaggerMovieComponent.builder().build()
        setContent {
            Jetpackcompose2022Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieList(movieData = mutableListOf(MovieData("First"), MovieData("Second")))
                }
            }
        }
    }
}

