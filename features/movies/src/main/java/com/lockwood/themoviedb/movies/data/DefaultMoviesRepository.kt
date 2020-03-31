package com.lockwood.themoviedb.movies.data

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.mapper.SearchMoviesResponseMapper
import com.lockwood.themoviedb.movies.data.source.MoviesRemoteDataSource
import com.lockwood.themoviedb.movies.domain.model.SearchMoviesResponse
import com.lockwood.themoviedb.movies.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class DefaultMoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val searchMoviesResponseMapper: SearchMoviesResponseMapper
) : MoviesRepository {

    override fun searchMovies(
        apiKey: String,
        query: String,
        page: Int,
        language: String
    ): Single<SearchMoviesResponse> {
        return moviesRemoteDataSource.searchMovies(apiKey, query, page, language)
            .map { searchMoviesResponseMapper.mapFromEntity(it) }
    }

}