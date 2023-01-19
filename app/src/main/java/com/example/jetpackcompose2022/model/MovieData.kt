package com.example.jetpackcompose2022.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true) // if you don't add this then moshi will not use kotlin codegen support for deserialize/serialize it will use reflection for this
data class MovieResponse(

    @Json(name = "success")
    val success: Boolean,
    @Json(name = "data")
    val movieData: MovieData
) {
    data class MovieData(
        @Json(name = "sections")
        val sections: List<MovieSections>
    )

    data class MovieSections(
        @Json(name = "title")
        val title: String,

        @Json(name = "description")
        val description: String,

        @Json(name = "image")
        val image: String
    )
}
