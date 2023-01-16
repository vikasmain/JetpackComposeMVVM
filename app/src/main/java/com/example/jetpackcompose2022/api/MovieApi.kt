package com.example.jetpackcompose2022.api

import com.example.jetpackcompose2022.model.MovieData
import retrofit2.http.GET

interface MovieApi {

    @GET("")
    fun getMovieList(): List<MovieData>
}
