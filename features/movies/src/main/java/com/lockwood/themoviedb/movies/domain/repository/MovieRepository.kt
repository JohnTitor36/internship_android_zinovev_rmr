package com.lockwood.themoviedb.movies.domain.repository

import com.lockwood.themoviedb.movies.domain.model.MovieResponse
import io.reactivex.Single

interface MovieRepository {

    companion object {

        private const val LOCALE_RU = "ru"
    }

    fun loadMovie(
        movieId: Int,
        language: String = LOCALE_RU
    ): Single<MovieResponse>

}