package com.example.jetpackcompose2022.api

import com.example.jetpackcompose2022.model.MovieResponse
import retrofit2.http.GET

interface MovieApi {

    @GET("7095430e-535a-4522-bbd3-8c1180dcf2df")
    suspend fun getMovieList(): MovieResponse
}
