package com.example.jetpackcompose2022.model

sealed class MovieSectionData {

    data class MovieHeader(
        val title: String,
        val noOfMovies: Int
    ) : MovieSectionData()

    data class MovieListContent(
        val movies: List<MovieResponse.MovieSections.Movie>
    ) : MovieSectionData()
}
