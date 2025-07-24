package com.example.movie.movieList.presentation

sealed class MovieListUiEvents {
    data class Paginate(val category: String) : MovieListUiEvents()
    object Navigate : MovieListUiEvents()
}