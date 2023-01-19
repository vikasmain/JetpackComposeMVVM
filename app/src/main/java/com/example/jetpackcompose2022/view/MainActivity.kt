package com.example.jetpackcompose2022.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.activity.viewModels
import com.example.jetpackcompose2022.deps.DaggerMovieComponent
import com.example.jetpackcompose2022.repository.MovieRepository
import com.example.jetpackcompose2022.ui.movielist.MovieList
import com.example.jetpackcompose2022.ui.theme.Jetpackcompose2022Theme
import com.example.jetpackcompose2022.viewmodel.MovieScreenState
import com.example.jetpackcompose2022.viewmodel.MovieViewModel
import com.example.jetpackcompose2022.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var scope: CoroutineScope

    @Inject
    lateinit var movieApiRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = DaggerMovieComponent.builder().build()
        component.inject(this)

        setContent {
            Jetpackcompose2022Theme {
                val viewModel: MovieViewModel by viewModels {
                    MovieViewModelFactory(movieRepository = movieApiRepository, scope)
                }
                viewModel.fetchList()
                // A surface container using the 'background' color from the theme
                movieScreen(viewModel = viewModel, this)
            }
        }
    }
}

@Composable
fun movieScreen(viewModel: MovieViewModel, activity: MainActivity) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        //you cannot use stateflow observing here using coroutine scope because then you can't
        //call compose methods from inside it, because then it will be a coroutine context instead
        //of compose context.

        val state = viewModel.screenStateFlow.collectAsState().value
        Log.d("RideActivity", "dar " + state)
        when (state) {
            is MovieScreenState.Success -> {
                Toast.makeText(activity, "" + state.movieData, Toast.LENGTH_LONG).show()
                state.movieData.movieData?.sections?.let {
                    MovieList(movieData = state.movieData.movieData?.sections)
                }
            }
            MovieScreenState.Loading -> {

            }
            is MovieScreenState.Error -> {

            }
        }
    }
}
