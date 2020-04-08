package com.lockwood.themoviedb.movies.data.repository

import com.lockwood.themoviedb.movies.data.model.MovieResponseEntity
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import io.reactivex.Observable
import io.reactivex.Single

interface MovieRemote {

    fun loadMovie(
        movieId: Int,
        language: String
    ): Single<MovieResponseEntity>

}