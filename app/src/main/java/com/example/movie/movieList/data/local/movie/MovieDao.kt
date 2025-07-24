package com.example.movie.movieList.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface MovieDao {
    @Upsert
    suspend fun upserMovieList(movieList:List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity Where id = :Id")
    suspend fun getMovieById(Id : Int) : MovieEntity

    @Query("SELECT * FROM MovieEntity Where category = :Category")
    suspend fun getMovieListByCategory(Category : String) : List<MovieEntity>
}