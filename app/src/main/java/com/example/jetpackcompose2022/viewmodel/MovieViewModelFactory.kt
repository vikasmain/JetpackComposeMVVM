package com.example.jetpackcompose2022.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcompose2022.repository.MovieRepository

class MovieViewModelFactory(
    private val movieRepository: MovieRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepository, dispatcherProvider) as T
    }
}
