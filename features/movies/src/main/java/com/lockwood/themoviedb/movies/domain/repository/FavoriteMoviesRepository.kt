package com.lockwood.themoviedb.movies.domain.repository

import com.lockwood.core.extensions.DEFAULT_LOCALE
import com.lockwood.themoviedb.movies.domain.model.MarkAsFavoriteBody
import com.lockwood.themoviedb.movies.domain.model.SearchMoviesResponse
import io.reactivex.Completable
import io.reactivex.Observable

interface FavoriteMoviesRepository {

    fun markAsFavorite(
        accountId: String,
        markAsFavoriteBody: MarkAsFavoriteBody
    ): Completable

    // TODO: добавить name
    fun loadFavoriteMovies(
        accountId: String,
        page: Int = 1,
        language: String = String.DEFAULT_LOCALE
    ): Observable<SearchMoviesResponse>

}