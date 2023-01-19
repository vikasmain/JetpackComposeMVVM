package com.example.jetpackcompose2022.api

import com.example.jetpackcompose2022.model.MovieData
import retrofit2.http.GET

interface MovieApi {

    @GET("e0aa53d5-4cb4-4970-aa81-c5581599c3a4")
    suspend fun getMovieList(): MovieData
}
