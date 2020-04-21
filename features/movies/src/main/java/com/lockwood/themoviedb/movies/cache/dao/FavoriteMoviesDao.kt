package com.lockwood.themoviedb.movies.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lockwood.themoviedb.movies.cache.model.SearchMoviesResponseDb.Result

@Dao
abstract class FavoriteMoviesDao {

    @Query("SELECT * FROM favorite_movies")
    abstract fun getFavoriteMovies(): List<Result>

    @Query("DELETE FROM favorite_movies")
    abstract fun clearFavoriteMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFavoriteMovies(list: List<Result>)

}