package com.example.movie.di

import android.app.Application
import androidx.room.Room
import com.example.movie.movieList.data.local.movie.MovieDatabase
import com.example.movie.movieList.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    private val interceptor : HttpLoggingInterceptor=
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    private val client : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun providesMovieApi():MovieApi
    = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(MovieApi.BASE_URL)
        .client(client)
        .build()
        .create(MovieApi::class.java)

    @Singleton
    @Provides
    fun moviesDatabase(application: Application): MovieDatabase =
        Room.databaseBuilder(
            application,
            MovieDatabase::class.java,
            "movie.Bd"
        ).build()

}