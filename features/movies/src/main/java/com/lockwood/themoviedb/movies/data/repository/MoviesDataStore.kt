package com.lockwood.themoviedb.movies.data.repository

import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.remote.model.response.SearchMoviesResponseModel
import io.reactivex.Observable
import retrofit2.http.Query

interface MoviesDataStore {

    fun searchMovies(
        query: String,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponseEntity>

//    fun searchGenres(
//      language: String
//    ): Observable<SearchMoviesResponseEntity>

}