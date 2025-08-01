package com.example.movie.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movie.core.presentation.components.MovieItem
import com.example.movie.movieList.presentation.MovieListState
import com.example.movie.movieList.presentation.MovieListUiEvents
import com.example.movie.movieList.util.Category

@Composable
fun PopularMovieScreen(
    movieListState: MovieListState,
    navHostController: NavHostController,
    onEvent: (MovieListUiEvents) -> Unit
) {
    if (movieListState.popularMovieList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()

        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
               items(movieListState.popularMovieList.size){ index ->
                   MovieItem(movie = movieListState.popularMovieList[index], navHostController = navHostController)
                   Spacer(modifier = Modifier.height(16.dp))
                   if( index >= movieListState.popularMovieList.size -1 && !movieListState.isLoading){
                       onEvent(MovieListUiEvents.Paginate(Category.POPULAR))
                   }
               }

        }
    }

}

