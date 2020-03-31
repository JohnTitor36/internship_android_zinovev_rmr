package com.lockwood.themoviedb.movies.data.repository

import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import io.reactivex.Single

interface MoviesRemote {

    fun searchMovies(
        apiKey: String,
        query: String,
        page: Int,
        language: String
    ): Single<SearchMoviesResponseEntity>

}