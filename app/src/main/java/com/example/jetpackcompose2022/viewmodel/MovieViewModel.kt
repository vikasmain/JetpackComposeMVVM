package com.example.jetpackcompose2022.viewmodel

import com.example.jetpackcompose2022.model.MovieData
import com.example.jetpackcompose2022.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val coroutineScope: CoroutineScope
) {

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
    data class Success(val movieData: MovieData) : MovieScreenState()
    object Loading : MovieScreenState()
    data class Error(val error: Throwable) : MovieScreenState()
}
