package com.lockwood.themoviedb.movies.data.repository

import com.lockwood.themoviedb.movies.data.model.MarkAsFavoriteBodyEntity
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface FavoriteMoviesRemote {

    fun markAsFavorite(
        accountId: Int,
        markAsFavoriteBody: MarkAsFavoriteBodyEntity
    ): Completable

    fun loadFavoriteMovies(
        accountId: Int,
        page: Int = 1,
        language: String
    ): Observable<SearchMoviesResponseEntity>

}