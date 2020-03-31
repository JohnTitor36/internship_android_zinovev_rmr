package com.lockwood.themoviedb.movies.domain.repository

import com.lockwood.themoviedb.movies.domain.model.SearchMoviesResponse
import io.reactivex.Single

interface MoviesRepository {

    companion object {

        private const val LOCALE_RU = "ru_RU"
    }

    fun searchMovies(
        apiKey: String,
        query: String,
        page: Int = 1,
        language: String = LOCALE_RU
    ): Single<SearchMoviesResponse>


}