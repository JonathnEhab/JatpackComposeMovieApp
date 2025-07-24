package com.example.movie.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie.R
import com.example.movie.movieList.presentation.MovieListUiEvents
import com.example.movie.movieList.presentation.MovieListViewModel
import com.example.movie.movieList.util.Screen

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavHostController) {
    val movieListViewModel = hiltViewModel<MovieListViewModel>()
    val movieState = movieListViewModel.movieListState.collectAsState().value
    val bottomNaveController = rememberNavController()

    Scaffold(bottomBar = {
        ButtonNavigationBar(
            bottomNavyController = bottomNaveController,
            onEvent = movieListViewModel::onEvent
        )
    }, topBar = {
        TopAppBar(
            title = {
                Text(
                    text = if (movieState.isCurrentPopularScreen) {
                        stringResource(R.string.popular)
                    } else {
                        stringResource(R.string.upcoming)
                    }, fontSize = 20.sp
                )
            },
            modifier = Modifier.shadow(5.dp),
            colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.inverseOnSurface)

        )
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            NavHost(
                navController = bottomNaveController,
                startDestination = Screen.PopularMovie.rout
            ) {
                composable(Screen.PopularMovie.rout) {
                    PopularMovieScreen(
                        navHostController = navController,
                        movieListState = movieState,
                        onEvent = movieListViewModel::onEvent
                    )
                }
                composable(Screen.Upcoming.rout) {
                    UpcomingMovieScreen(
                        navHostController = navController,
                        movieListState = movieState,
                        onEvent = movieListViewModel::onEvent
                    )
                }
            }
        }

    }

}

@Composable
fun ButtonNavigationBar(
    bottomNavyController: NavHostController, onEvent: (MovieListUiEvents) -> Unit
) {
    val item = listOf(
        bottomItem(title = stringResource(R.string.popular_movie), icon = Icons.Rounded.Movie),
        bottomItem(title = stringResource(R.string.upcoming_movie), icon = Icons.Rounded.Upcoming)
    )
    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)

        ) {
            item.forEachIndexed { index, bottomItem ->
                NavigationBarItem(selected = selected.intValue == index,
                    onClick = {
                        selected.intValue = index
                        when (selected.intValue) {
                            0 -> {
                                onEvent(MovieListUiEvents.Navigate)
                                bottomNavyController.popBackStack()
                                bottomNavyController.navigate(Screen.PopularMovie.rout)

                            }

                            1 -> {

                                onEvent(MovieListUiEvents.Navigate)
                                bottomNavyController.popBackStack()
                                bottomNavyController.navigate(Screen.Upcoming.rout)
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = bottomItem.icon,
                            contentDescription = bottomItem.title,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            text = bottomItem.title,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }
    }


}

data class bottomItem(
    val title: String, val icon: ImageVector
)