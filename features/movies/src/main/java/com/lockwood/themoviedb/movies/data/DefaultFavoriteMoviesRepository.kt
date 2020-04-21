package com.lockwood.themoviedb.movies.data

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.mapper.MarkAsFavoriteBodyMapper
import com.lockwood.themoviedb.movies.data.mapper.SearchMoviesResponseMapper
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
    private val markAsFavoriteBodyMapper: MarkAsFavoriteBodyMapper,
    private val searchMoviesResponseMapper: SearchMoviesResponseMapper
) : FavoriteMoviesRepository {

    override fun markAsFavorite(
        accountId: Int,
        markAsFavoriteBody: MarkAsFavoriteBody
    ): Completable {
        val body = markAsFavoriteBodyMapper.mapToEntity(markAsFavoriteBody)
        return favoriteMoviesRemoteDataSource.markAsFavorite(accountId, body)
    }

    override fun loadFavoriteMovies(
        accountId: Int,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponse> {
        return favoriteMoviesRemoteDataSource.loadFavoriteMovies(accountId, page, language)
            .map { searchMoviesResponseMapper.mapFromEntity(it) }
    }

}