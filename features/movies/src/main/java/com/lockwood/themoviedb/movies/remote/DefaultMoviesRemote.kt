package com.lockwood.themoviedb.movies.remote

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.data.repository.MoviesRemote
import com.lockwood.themoviedb.movies.remote.mapper.SearchMoviesResponseMapper
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class DefaultMoviesRemote @Inject constructor(
    private val moviesService: MoviesService,
    private val searchMoviesResponseMapper: SearchMoviesResponseMapper
) : MoviesRemote {

    override fun searchMovies(
        apiKey: String,
        query: String,
        page: Int,
        language: String
    ): Single<SearchMoviesResponseEntity> {
        return moviesService.searchMovies(apiKey, query, page, language)
            .map { searchMoviesResponseMapper.mapFromRemote(it) }
    }


}
