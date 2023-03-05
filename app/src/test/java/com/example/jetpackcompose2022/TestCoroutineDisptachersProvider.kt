package com.example.jetpackcompose2022

import com.example.jetpackcompose2022.viewmodel.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestCoroutineDispatchersProvider: DispatcherProvider {
    private val dispatchers = TestCoroutineDispatcher()

    override val main: CoroutineDispatcher
        get() = dispatchers
    override val io: CoroutineDispatcher
        get() = dispatchers
    override val computation: CoroutineDispatcher
        get() = dispatchers
}
