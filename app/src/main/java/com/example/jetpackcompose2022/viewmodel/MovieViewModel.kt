package com.example.jetpackcompose2022.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jetpackcompose2022.model.MovieResponse
import com.example.jetpackcompose2022.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val coroutineScope: CoroutineScope
): ViewModel() {

    internal val screenStateFlow = MutableStateFlow<MovieScreenState>(MovieScreenState.Loading)

    fun fetchList() {
        movieRepository.getMovieList()
            .onStart {
                screenStateFlow.value = MovieScreenState.Loading
            }
            .onEach {
                screenStateFlow.value = MovieScreenState.Success(it)
            }
            .catch {
                screenStateFlow.value = MovieScreenState.Error(it)
            }
            .launchIn(coroutineScope)
    }
}

sealed class MovieScreenState {
    data class Success(val movieData: MovieResponse) : MovieScreenState()
    object Loading : MovieScreenState()
    data class Error(val error: Throwable) : MovieScreenState()
}
