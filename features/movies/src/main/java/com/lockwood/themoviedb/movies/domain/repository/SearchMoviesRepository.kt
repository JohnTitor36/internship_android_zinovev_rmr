package com.lockwood.themoviedb.movies.domain.repository

import com.lockwood.core.extensions.DEFAULT_LOCALE
import com.lockwood.themoviedb.movies.domain.model.SearchMoviesResponse
import io.reactivex.Observable

interface SearchMoviesRepository {

    fun searchMovies(
        query: String,
        page: Int = 1,
        language: String = String.DEFAULT_LOCALE
    ): Observable<SearchMoviesResponse>

}