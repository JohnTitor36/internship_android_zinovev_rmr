package com.lockwood.themoviedb.movies.data

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.mapper.MarkAsFavoriteBodyMapper
import com.lockwood.themoviedb.movies.data.mapper.SearchMoviesResponseMapper
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.data.source.FavoriteMoviesCacheDataSource
import com.lockwood.themoviedb.movies.data.source.FavoriteMoviesRemoteDataSource
import com.lockwood.themoviedb.movies.domain.model.MarkAsFavoriteBody
import com.lockwood.themoviedb.movies.domain.model.SearchMoviesResponse
import com.lockwood.themoviedb.movies.domain.repository.FavoriteMoviesRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

@FeatureScope
class DefaultFavoriteMoviesRepository @Inject constructor(
    private val favoriteMoviesRemoteDataSource: FavoriteMoviesRemoteDataSource,
    private val favoriteMoviesCacheDataSource: FavoriteMoviesCacheDataSource,
    private val markAsFavoriteBodyMapper: MarkAsFavoriteBodyMapper,
    private val searchMoviesResponseMapper: SearchMoviesResponseMapper
) : FavoriteMoviesRepository {

    override fun markAsFavorite(
        accountId: String,
        markAsFavoriteBody: MarkAsFavoriteBody
    ): Completable {
        val body = markAsFavoriteBodyMapper.mapToEntity(markAsFavoriteBody)
        return favoriteMoviesRemoteDataSource.markAsFavorite(accountId, body)
    }

    override fun loadFavoriteMovies(
        accountId: String,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponse> {
        return fetchAndSaveMovies(accountId, page, language)
            .andThen(getOfflineMovies(accountId, page, language))
            .onErrorResumeNext(getOfflineMovies(accountId, page, language))
            .map { searchMoviesResponseMapper.mapFromEntity(it) }
    }

    override fun saveMovies(list: List<SearchMoviesResponse>): Completable {
        val movies = list.map { searchMoviesResponseMapper.mapToEntity(it) }
        return favoriteMoviesCacheDataSource.saveMovies(movies)
    }

    private fun fetchAndSaveMovies(
        accountId: String,
        page: Int,
        language: String
    ): Completable {
        return favoriteMoviesRemoteDataSource.loadFavoriteMovies(accountId, page, language)
            .toList()
            .flatMapCompletable { favoriteMoviesCacheDataSource.saveMovies(it) }
    }

    private fun getOfflineMovies(
        accountId: String,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponseEntity> {
        return favoriteMoviesCacheDataSource.loadFavoriteMovies(accountId, page, language)
    }


}