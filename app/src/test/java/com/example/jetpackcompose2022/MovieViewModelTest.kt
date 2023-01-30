package com.example.jetpackcompose2022

import com.example.jetpackcompose2022.model.MovieResponse
import com.example.jetpackcompose2022.model.MovieSectionData
import com.example.jetpackcompose2022.repository.MovieRepository
import com.example.jetpackcompose2022.viewmodel.MovieScreenState
import com.example.jetpackcompose2022.viewmodel.MovieViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.plus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.IOException

class MovieViewModelTest {
    private val apiRepository = mock<MovieRepository>()
    private val coroutineScope = MainScope() + Dispatchers.Unconfined
    val viewModel = MovieViewModel(apiRepository, coroutineScope)
    val movies = mutableListOf(
        MovieResponse.MovieSections.Movie(
            title = "Fast and furious",
            description = "Action movie",
            image = "https://google.com"
        )
    )
    val movieSections = mutableListOf(
        MovieResponse.MovieSections(
            index = 0,
            noOfMovies = 10,
            movies = movies,
            title = "Available movies"
        )
    )
    val movieResponse = MovieResponse(movieData = MovieResponse.MovieData(sections = movieSections))

    @Test
    fun testApiSuccessUseCase() {
        val testData = flowOf(movieResponse)
        val movieSectionData = mutableListOf<MovieSectionData>()
        movieResponse.movieData?.sections?.forEach {
            movieSectionData.add(MovieSectionData.MovieHeader("Available movies", 10))
            movieSectionData.addAll(getMovieListContent(it.movies))
        }

        whenever(apiRepository.getMovieList()).thenReturn(testData)
        viewModel.fetchList()
        assertEquals(MovieScreenState.Success(movieSectionData), viewModel.screenStateFlow.value)
    }

    fun getMovieListContent(movie: List<MovieResponse.MovieSections.Movie>): List<MovieSectionData.MovieListContent> {
        val list = mutableListOf<MovieSectionData.MovieListContent>()
        movie.map {
            list.add(MovieSectionData.MovieListContent(it.title, it.description, it.image))
        }
        return list
    }
}
