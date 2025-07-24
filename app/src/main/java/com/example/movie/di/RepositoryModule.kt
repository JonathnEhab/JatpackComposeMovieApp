package com.example.movie.di

import com.example.movie.movieList.data.repository.MovieRepositoryImpl
import com.example.movie.movieList.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsMoviesListRepository(repositoryImpl: MovieRepositoryImpl) : MovieRepository

}