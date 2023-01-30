package com.example.jetpackcompose2022.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose2022.model.MovieResponse
import com.example.jetpackcompose2022.model.MovieSectionData
import com.example.jetpackcompose2022.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    internal val screenStateFlow = MutableStateFlow<MovieScreenState>(MovieScreenState.Loading)

    fun fetchList() {
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
            .launchIn(coroutineScope)
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
