package com.lockwood.themoviedb.movies.domain.repository

import com.lockwood.themoviedb.movies.domain.model.SearchMoviesResponse
import io.reactivex.Observable

interface SearchMoviesRepository {

    companion object {

        private const val LOCALE_RU = "ru"
    }

    fun searchMovies(
        query: String,
        page: Int = 1,
        language: String = LOCALE_RU
    ): Observable<SearchMoviesResponse>

}