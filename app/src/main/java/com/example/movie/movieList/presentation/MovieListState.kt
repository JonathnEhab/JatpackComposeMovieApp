package com.example.movie.movieList.presentation

import com.example.movie.movieList.domain.Movie

data class MovieListState (
    val isLoading : Boolean =false,
    val popularMovieListPage : Int = 1,
    val upComingMovieListPage : Int = 1,

    val isCurrentPopularScreen : Boolean = true,

    val popularMovieList : List<Movie> = emptyList(),
    val upComingMovieList : List<Movie> = emptyList()

)