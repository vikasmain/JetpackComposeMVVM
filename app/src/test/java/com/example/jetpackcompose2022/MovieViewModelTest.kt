package com.example.jetpackcompose2022

import com.example.jetpackcompose2022.repository.MovieRepository
import com.example.jetpackcompose2022.viewmodel.MovieViewModel
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.plus
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class MovieViewModelTest {
    private val apiRepository = mock<MovieRepository>()
    private val coroutineScope = MainScope() + Dispatchers.Unconfined
    val viewModel = MovieViewModel(apiRepository, coroutineScope)
}
