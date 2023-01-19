package com.example.jetpackcompose2022.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcompose2022.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope

class MovieViewModelFactory(
    private val movieRepository: MovieRepository,
    private val scope: CoroutineScope
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepository, scope) as T
    }
}
