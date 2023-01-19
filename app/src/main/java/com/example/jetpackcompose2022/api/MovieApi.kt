package com.example.jetpackcompose2022.api

import com.example.jetpackcompose2022.model.MovieResponse
import retrofit2.http.GET

interface MovieApi {

    @GET("ef5b9431-5da5-42d9-aaba-9d975e9bf4cd")
    suspend fun getMovieList(): MovieResponse
}
