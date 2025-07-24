package com.example.movie.details.presentation


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.movieList.domain.repository.MovieRepository
import com.example.movie.movieList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val movieId = savedStateHandle.get<Int>("movieId")
    private val _detailsSata = MutableStateFlow(DetailsState())
    val detailsState = _detailsSata.asStateFlow()

    init {
        getMovie(movieId ?: -1)
    }

    private fun getMovie(id: Int) {
        viewModelScope.launch {
            _detailsSata.update {
                it.copy(isLoading = true)
            }
            repository.getMovie(id).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _detailsSata.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        _detailsSata.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let {movie ->
                            _detailsSata.update {
                                it.copy(movie = movie)
                            }
                        }
                    }
                }
            }
        }

    }

}