package com.example.movie.core.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movie.details.presentation.DetailsScreen
import com.example.movie.movieList.util.Screen
import com.example.movie.ui.theme.MovieTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieTheme {
                SetBarColor(color = MaterialTheme.colorScheme.inverseOnSurface)
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.rout,
                    ) {
                        composable(Screen.Home.rout) {
                            HomeScreen(navController)
                        }
                        composable(
                            Screen.Details.rout + "/{movieId}",
                            arguments = listOf(navArgument("movieId")
                            { type = NavType.IntType })
                        ) {
                             DetailsScreen()
                        }
                    }
                }
            }
        }
    }
    @Composable
    private fun SetBarColor(color: Color){
        val setSystemUiController = rememberSystemUiController()
        LaunchedEffect(key1 = color) {
            setSystemUiController.setSystemBarsColor(color = color)
            
        }
    }
}

