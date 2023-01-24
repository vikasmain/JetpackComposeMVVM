package com.example.jetpackcompose2022.api

import com.example.jetpackcompose2022.model.MovieResponse
import retrofit2.http.GET

interface MovieApi {

    @GET("70dca5b6-2e1c-45e3-baf5-43edb5c73617")
    suspend fun getMovieList(): MovieResponse
}
