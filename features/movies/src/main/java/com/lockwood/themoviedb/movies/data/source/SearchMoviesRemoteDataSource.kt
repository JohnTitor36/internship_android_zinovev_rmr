package com.lockwood.themoviedb.movies.data.source

import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.data.repository.SearchMoviesDataStore
import com.lockwood.themoviedb.movies.data.repository.SearchMoviesRemote
import io.reactivex.Observable
import javax.inject.Inject

class SearchMoviesRemoteDataSource @Inject constructor(
    private val moviesRemote: SearchMoviesRemote
) : SearchMoviesDataStore {

    override fun searchMovies(
        query: String,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponseEntity> {
        return moviesRemote.searchMovies(query, page, language)
    }

}