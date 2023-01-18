package com.example.jetpackcompose2022.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true) // if you don't add this then moshi will not use kotlin codegen support for deserialize/serialize it will use reflection for this
data class MovieData(
    @Json(name = "movie_list")
    val movieList: List<MovieList>
) {
    data class MovieList(
        @Json(name = "title")
        val title: String,

        @Json(name = "description")
        val description: String
    )
}
