package com.example.movie.movieList.util

sealed class Screen(val rout : String) {
    object Home:Screen("home")
    object PopularMovie:Screen("popular")
    object Upcoming:Screen("upComing")
    object Details:Screen("details")
}