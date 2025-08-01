package com.example.movie.movieList.data.remote

import com.example.movie.movieList.data.remote.respond.MovieDto
import com.example.movie.movieList.data.remote.respond.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{category}")
    suspend fun getMoviesList(
        @Path("category")category: String,
        @Query("page") page:Int,
        @Query("api_key") apiKey :String = API_KEY
    ):MovieListDto

    companion object {

        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "50b13e010d72170b1aab1fba34aa753f"

    }
}