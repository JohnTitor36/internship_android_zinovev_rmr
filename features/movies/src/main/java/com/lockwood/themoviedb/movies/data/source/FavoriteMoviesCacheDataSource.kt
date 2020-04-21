package com.lockwood.themoviedb.movies.data.source

import com.lockwood.themoviedb.movies.data.model.MarkAsFavoriteBodyEntity
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.data.repository.FavoriteMoviesCache
import com.lockwood.themoviedb.movies.data.repository.FavoriteMoviesDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class FavoriteMoviesCacheDataSource @Inject constructor(
    private val moviesCache: FavoriteMoviesCache
) : FavoriteMoviesDataStore {

    override fun markAsFavorite(
        accountId: String,
        markAsFavoriteBody: MarkAsFavoriteBodyEntity
    ): Completable {
        return moviesCache.markAsFavorite(accountId, markAsFavoriteBody)
    }

    override fun loadFavoriteMovies(
        accountId: String,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponseEntity> {
        return moviesCache.loadFavoriteMovies(accountId, page, language)
    }

    override fun saveMovies(list: List<SearchMoviesResponseEntity>): Completable {
        return moviesCache.saveMovies(list)
    }

}