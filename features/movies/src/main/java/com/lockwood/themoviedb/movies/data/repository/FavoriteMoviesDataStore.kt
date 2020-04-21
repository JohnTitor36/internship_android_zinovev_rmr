package com.lockwood.themoviedb.movies.data.repository

import com.lockwood.themoviedb.movies.data.model.MarkAsFavoriteBodyEntity
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface FavoriteMoviesDataStore {

    fun markAsFavorite(
        accountId: String,
        markAsFavoriteBody: MarkAsFavoriteBodyEntity
    ): Completable

    fun loadFavoriteMovies(
        accountId: String,
        page: Int = 1,
        language: String
    ): Observable<SearchMoviesResponseEntity>

}