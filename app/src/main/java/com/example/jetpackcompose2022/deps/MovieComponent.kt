package com.example.jetpackcompose2022.deps

import com.squareup.moshi.Moshi
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

const val BASE_URL = ""

@Component(modules = [AppModule::class])
@Singleton
interface MovieComponent {
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
        fun providesMoshi(): Moshi {
            return Moshi.Builder().build()
        }

        @Provides
        @Singleton
        fun providesRetrofitClient(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
            return Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL).build()
        }
    }
}
