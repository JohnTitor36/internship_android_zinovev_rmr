package com.lockwood.themoviedb.movies.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lockwood.themoviedb.movies.cache.dao.FavoriteMoviesDao
import com.lockwood.themoviedb.movies.cache.model.SearchMoviesResponseDb

@Database(
    entities = [
        SearchMoviesResponseDb.Result::class
    ],
    version = 1
)
abstract class FavoriteMoviesDatabase : RoomDatabase() {

    abstract fun favoriteMoviesDao(): FavoriteMoviesDao

}