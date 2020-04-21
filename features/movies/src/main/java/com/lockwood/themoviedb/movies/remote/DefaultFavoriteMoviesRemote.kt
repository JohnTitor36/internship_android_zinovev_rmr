package com.lockwood.themoviedb.movies.remote

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.model.MarkAsFavoriteBodyEntity
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.data.repository.FavoriteMoviesRemote
import com.lockwood.themoviedb.movies.remote.mapper.MarkAsFavoriteBodyMapper
import com.lockwood.themoviedb.movies.remote.mapper.SearchMoviesResponseMapper
import com.lockwood.themoviedb.movies.remote.service.FavoriteMoviesService
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

@FeatureScope
class DefaultFavoriteMoviesRemote @Inject constructor(
    private val favoriteMoviesService: FavoriteMoviesService,
    private val markAsFavoriteBodyMapper: MarkAsFavoriteBodyMapper,
    private val searchMoviesResponseMapper: SearchMoviesResponseMapper
) : FavoriteMoviesRemote {

    override fun markAsFavorite(
        accountId: Int,
        markAsFavoriteBody: MarkAsFavoriteBodyEntity
    ): Completable {
        val body = markAsFavoriteBodyMapper.mapToRemote(markAsFavoriteBody)
        return favoriteMoviesService.markAsFavorite(accountId, body)
    }

    override fun loadFavoriteMovies(
        accountId: Int,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponseEntity> {
        return favoriteMoviesService.loadFavoriteMovies(accountId, page, language).map {
            searchMoviesResponseMapper.mapFromRemote(it)
        }
    }
}
