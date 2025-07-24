package com.example.movie.movieList.data.repository

import com.example.movie.movieList.data.local.movie.MovieDatabase
import com.example.movie.movieList.data.mappers.toMovie
import com.example.movie.movieList.data.mappers.toMovieEntity
import com.example.movie.movieList.data.remote.MovieApi
import com.example.movie.movieList.domain.Movie
import com.example.movie.movieList.domain.repository.MovieRepository
import com.example.movie.movieList.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val database: MovieDatabase
) :
    MovieRepository {
    override suspend fun getMovieList(
        fetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> =
        flow {

            //loading
            emit(Resource.Loading(true))
            //get movie from database
            val localMovieList =database.movieDao.getMovieListByCategory(category)
            // in case that empty and do not fetchFromRemote
            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !fetchFromRemote

            // if true
            if (shouldLoadLocalMovie){
                //map data  when success
                emit(Resource.Success(data = localMovieList.map { movieEntity ->
                    movieEntity.toMovie(category)
                }))
                // stop loading
                emit(Resource.Loading(false))
                return@flow
            }

            // if shouldLoadLocalMovie = false
           val movieFromApi = try {
               // make network call
               movieApi.getMoviesList(category,page)
               // exceptions happens
           }catch (e: IOException){
               e.printStackTrace()
               emit(Resource.Error(message = "Error loading movies / IOException"))
               return@flow
           }
           catch (e: HttpException){
               e.printStackTrace()
               emit(Resource.Error(message = "Error loading movies / HttpException"))
               return@flow
           }catch (e: Exception){
               e.printStackTrace()
               emit(Resource.Error(message = "Error loading movies / Exception"))
               return@flow
           }
            // map api response to toMovieEntity
            val movieEntities = movieFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            }
            // store in data base
            database.movieDao.upserMovieList(movieEntities)
            // get list of movie when success
            emit(Resource.Success(movieEntities.map { it.toMovie(category) }))
            // stop loading
            emit(Resource.Loading(false))
        }


    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> =
        flow {
            emit(Resource.Loading(true))
            val movieEntity = database.movieDao.getMovieById(id)
            if (movieEntity != null){
                emit(Resource.Success(movieEntity.toMovie(movieEntity.category)))
                emit(Resource.Loading(false))
                return@flow
            }
            emit(Resource.Error("This Movie does not store in database"))
            emit(Resource.Loading(false))
            

        }
}