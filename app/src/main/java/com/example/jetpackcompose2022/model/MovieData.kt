package com.example.jetpackcompose2022.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("data")
    val movieData: MovieData?
) {
    data class MovieData(
        @SerializedName("sections")
        val sections: List<MovieSections>
    )

    data class MovieSections(
        @SerializedName("title")
        val title: String,

        @SerializedName("index")
        val index: String,

        @SerializedName("no_of_movies")
        val noOfMovies: Int,

        @SerializedName("movie")
        val movies: List<Movie>
    ) {
        data class Movie(
            @SerializedName("title")
            val title: String,

            @SerializedName("description")
            val description: String,

            @SerializedName("image")
            val image: String
        )
    }
}
