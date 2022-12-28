package com.example.jetpackcompose2022.deps

import com.example.jetpackcompose2022.view.MainActivity
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.plus
import javax.inject.Scope
import javax.inject.Singleton

@Subcomponent(modules = [MovieModule::class])
@Singleton
interface MovieComponent : AppComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MovieComponent
    }
}

@Module
class MovieModule {
    companion object {

        @Provides
        @MovieActivityScope
        fun providesCoroutineScope(): CoroutineScope {
            return MainScope() + CoroutineName("Movie")
        }
    }
}

@Scope
@Retention(AnnotationRetention.SOURCE)
internal annotation class MovieActivityScope
