package com.lockwood.themoviedb.movies.data.source

import com.lockwood.themoviedb.movies.data.model.MarkAsFavoriteBodyEntity
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.data.repository.FavoriteMoviesDataStore
import com.lockwood.themoviedb.movies.data.repository.FavoriteMoviesRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class FavoriteMoviesRemoteDataSource @Inject constructor(
    private val moviesRemote: FavoriteMoviesRemote
) : FavoriteMoviesDataStore {

    override fun markAsFavorite(
        accountId: Int,
        markAsFavoriteBody: MarkAsFavoriteBodyEntity
    ): Completable {
        return moviesRemote.markAsFavorite(accountId, markAsFavoriteBody)
    }

    override fun loadFavoriteMovies(
        accountId: Int,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponseEntity> {
        return moviesRemote.loadFavoriteMovies(accountId, page, language)
    }

}