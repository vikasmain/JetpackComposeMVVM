package com.example.jetpackcompose2022.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.jetpackcompose2022.deps.DaggerMovieComponent
import com.example.jetpackcompose2022.model.MovieData
import com.example.jetpackcompose2022.ui.movielist.MovieList
import com.example.jetpackcompose2022.ui.theme.Jetpackcompose2022Theme
import com.example.jetpackcompose2022.viewmodel.MovieScreenState
import com.example.jetpackcompose2022.viewmodel.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var scope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = DaggerMovieComponent.builder().build()
        viewModel.fetchList()

        setContent {
            Jetpackcompose2022Theme {
                // A surface container using the 'background' color from the theme
                movieScreen(viewModel = viewModel, scope)
            }
        }
    }
}


@Composable
fun movieScreen(viewModel: MovieViewModel, scope: CoroutineScope) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val state = viewModel.screenStateFlow.collectAsState().value
        when (state) {
            is MovieScreenState.Success -> {
                MovieList(movieData = state.movieData.movieList)
            }
            MovieScreenState.Loading -> {

            }
            is MovieScreenState.Error -> {

            }
        }
    }
}
