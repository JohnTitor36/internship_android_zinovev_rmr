package com.lockwood.themoviedb.movies.cache

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.cache.db.FavoriteMoviesDatabase
import com.lockwood.themoviedb.movies.cache.mapper.SearchMoviesResponseMapper
import com.lockwood.themoviedb.movies.cache.model.SearchMoviesResponseDb
import com.lockwood.themoviedb.movies.data.model.MarkAsFavoriteBodyEntity
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.data.repository.FavoriteMoviesCache
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

@FeatureScope
class DefaultFavoriteMoviesCache @Inject constructor(
    private val favoriteMoviesDatabase: FavoriteMoviesDatabase,
    private val searchMoviesResponseMapper: SearchMoviesResponseMapper
) : FavoriteMoviesCache {

    override fun loadFavoriteMovies(
        accountId: String,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponseEntity> {
        return Observable.fromCallable {
            favoriteMoviesDatabase.favoriteMoviesDao().getFavoriteMovies().map {
                val result = SearchMoviesResponseDb(results = listOf(it))
                searchMoviesResponseMapper.mapFromCached(result)
            }.first()
        }
    }

    override fun saveMovies(list: List<SearchMoviesResponseEntity>): Completable {
        return Completable.fromCallable {
            val results =
                list.map { searchMoviesResponseMapper.mapToCached(it) }.flatMap { it.results }
            favoriteMoviesDatabase.favoriteMoviesDao().insertFavoriteMovies(results)
        }
    }

    override fun markAsFavorite(
        accountId: String,
        markAsFavoriteBody: MarkAsFavoriteBodyEntity
    ): Completable {
        throw UnsupportedOperationException()
    }
}
