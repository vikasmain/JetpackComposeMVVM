package com.example.jetpackcompose2022.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose2022.model.MovieResponse
import com.example.jetpackcompose2022.model.MovieSectionData
import com.example.jetpackcompose2022.repository.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieRepository: MovieRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    internal val screenStateFlow = MutableStateFlow<MovieScreenState>(MovieScreenState.Loading)

    fun fetchList() {
        viewModelScope.launch(dispatcherProvider.main) {
            movieRepository.getMovieList()
                .onStart {
                    screenStateFlow.value = MovieScreenState.Loading
                }
                .onEach {
                    screenStateFlow.value = MovieScreenState.Success(mapMovieData(it))
                }
                .catch {
                    screenStateFlow.value = MovieScreenState.Error(it)
                }
        }
    }

    private fun mapMovieData(movieResponse: MovieResponse): List<MovieSectionData> {
        val movieSectionData = mutableListOf<MovieSectionData>()
        movieResponse.movieData?.sections?.forEach {
            movieSectionData.add(MovieSectionData.MovieHeader(it.title, it.noOfMovies))
            movieSectionData.addAll(mapMovieItem(it.movies))
        }
        return movieSectionData
    }

    fun mapMovieItem(movie: List<MovieResponse.MovieSections.Movie>): List<MovieSectionData.MovieListContent> {
        return movie.map {
            MovieSectionData.MovieListContent(
                title = it.title,
                description = it.description,
                image = it.image
            )
        }
    }
}

sealed class MovieScreenState {
    data class Success(val movieSectionData: List<MovieSectionData>) : MovieScreenState()
    object Loading : MovieScreenState()
    data class Error(val error: Throwable) : MovieScreenState()
}
