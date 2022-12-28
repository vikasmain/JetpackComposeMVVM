package com.example.jetpackcompose2022.deps

interface MovieDepsProvider {
    fun providesSubComponent(): MovieComponent.Builder
}