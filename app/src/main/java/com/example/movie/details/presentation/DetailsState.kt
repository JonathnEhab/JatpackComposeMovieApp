package com.example.movie.details.presentation

import com.example.movie.movieList.domain.Movie

data class DetailsState (
    val isLoading : Boolean= false,
    val movie :Movie?=null
)