package com.lockwood.themoviedb.movies.data.source

import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.data.repository.MoviesDataStore
import com.lockwood.themoviedb.movies.data.repository.MoviesRemote
import io.reactivex.Single
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val moviesRemote: MoviesRemote
) : MoviesDataStore {

    override fun searchMovies(
        apiKey: String,
        query: String,
        page: Int,
        language: String
    ): Single<SearchMoviesResponseEntity> {
        return moviesRemote.searchMovies(apiKey, query, page, language)
    }

}