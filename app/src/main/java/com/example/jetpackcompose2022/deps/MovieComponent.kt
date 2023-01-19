package com.example.jetpackcompose2022.deps

import com.example.jetpackcompose2022.api.MovieApi
import com.example.jetpackcompose2022.view.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.plus
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://run.mocky.io/v3/"

@Component(modules = [AppModule::class])
@Singleton
interface MovieComponent {

    fun inject(movieActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): MovieComponent
    }
}

@Module
class AppModule {

    companion object {

        @Provides
        @Singleton
        fun providesOkhttpClient(): OkHttpClient {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        }

        @Provides
        @Singleton
        fun providesRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build()
        }

        @Provides
        @Singleton
        fun providesCoroutineScope(): CoroutineScope {
            return MainScope() + CoroutineName("Movie")
        }

        @Provides
        @Singleton
        fun providesApiRepository(retrofit: Retrofit): MovieApi {
            return retrofit.create(MovieApi::class.java)
        }
    }
}
