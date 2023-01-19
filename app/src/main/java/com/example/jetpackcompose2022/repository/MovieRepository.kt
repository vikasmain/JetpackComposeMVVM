package com.example.jetpackcompose2022.repository

import com.example.jetpackcompose2022.api.MovieApi
import com.example.jetpackcompose2022.model.MovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi
) {

    fun getMovieList(): Flow<MovieData> {
        return flow {
            emit(movieApi.getMovieList())
        }.flowOn(Dispatchers.IO)
    }
}
