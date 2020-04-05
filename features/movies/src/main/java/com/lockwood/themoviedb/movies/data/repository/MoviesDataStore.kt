package com.lockwood.themoviedb.movies.data.repository

import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import io.reactivex.Observable

interface MoviesDataStore {

    fun searchMovies(
        query: String,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponseEntity>

}