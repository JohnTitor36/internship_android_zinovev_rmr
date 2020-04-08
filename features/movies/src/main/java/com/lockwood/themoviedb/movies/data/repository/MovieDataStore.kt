package com.lockwood.themoviedb.movies.data.repository

import com.lockwood.themoviedb.movies.data.model.MovieResponseEntity
import io.reactivex.Single

interface MovieDataStore {

    fun loadMovie(
        movieId: Int,
        language: String
    ): Single<MovieResponseEntity>

}