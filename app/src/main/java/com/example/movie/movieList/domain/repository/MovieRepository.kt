package com.example.movie.movieList.domain.repository

import com.example.movie.movieList.domain.Movie
import com.example.movie.movieList.util.Resource
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    suspend fun getMovieList(fetchFromRemote: Boolean, category: String, page: Int)
    : Flow<Resource<List<Movie>>>

    suspend fun getMovie(id:Int):Flow<Resource<Movie>>
}