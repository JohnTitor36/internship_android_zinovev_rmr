package com.lockwood.themoviedb.movies.domain.repository

import com.lockwood.core.extensions.DEFAULT_LOCALE
import com.lockwood.themoviedb.movies.domain.model.MovieResponse
import io.reactivex.Single

interface MovieRepository {

    fun loadMovie(
        movieId: Int,
        language: String = String.DEFAULT_LOCALE
    ): Single<MovieResponse>

}