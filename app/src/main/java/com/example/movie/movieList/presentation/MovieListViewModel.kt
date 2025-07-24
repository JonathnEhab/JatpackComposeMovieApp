package com.example.movie.movieList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.movieList.domain.repository.MovieRepository
import com.example.movie.movieList.util.Category
import com.example.movie.movieList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject
constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    init {
        getPopularMovies(false)
        getUpcomingMovies(false)
    }

    fun onEvent(events: MovieListUiEvents) {
        when (events) {
            MovieListUiEvents.Navigate -> {
                _movieListState.update {
                    it.copy(
                        isCurrentPopularScreen = !movieListState.value.isCurrentPopularScreen
                    )
                }
            }

            is MovieListUiEvents.Paginate -> {
                if (events.category == Category.POPULAR) {
                    getPopularMovies(true)

                } else if (events.category == Category.UPCOMING) {
                    getUpcomingMovies(true)
                }
            }
        }


    }

    private fun getUpcomingMovies(forceFetchFromRemote: Boolean) {
        viewModelScope.launch() {
            _movieListState.update {
                it.copy(isLoading = true)
            }
            movieRepository.getMovieList(
                forceFetchFromRemote,
                Category.UPCOMING,
                movieListState.value.upComingMovieListPage
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { upComing ->
                            _movieListState.update {
                                it.copy(upComingMovieList = movieListState.value.upComingMovieList
                                +upComing.shuffled(),
                                    upComingMovieListPage = movieListState.value.upComingMovieListPage+1
                                )
                            }
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                }

            }
        }

    }

    private fun getPopularMovies(forceFetchFromRemote: Boolean) {
        viewModelScope.launch() {
            _movieListState.update {
                it.copy(isLoading = true)
            }
            movieRepository.getMovieList(
                forceFetchFromRemote,
                Category.POPULAR,
                movieListState.value.popularMovieListPage
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { popular ->
                            _movieListState.update {
                                it.copy(popularMovieList = movieListState.value.popularMovieList
                                        +popular.shuffled(),
                                    popularMovieListPage = movieListState.value.popularMovieListPage+1
                                )
                            }
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                }

            }
        }
    }

}