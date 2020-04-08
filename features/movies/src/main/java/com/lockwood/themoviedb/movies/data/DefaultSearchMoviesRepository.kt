package com.lockwood.themoviedb.movies.data

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.mapper.SearchMoviesResponseMapper
import com.lockwood.themoviedb.movies.data.source.SearchMoviesRemoteDataSource
import com.lockwood.themoviedb.movies.domain.model.SearchMoviesResponse
import com.lockwood.themoviedb.movies.domain.repository.SearchMoviesRepository
import io.reactivex.Observable
import javax.inject.Inject

@FeatureScope
class DefaultSearchMoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: SearchMoviesRemoteDataSource,
    private val searchMoviesResponseMapper: SearchMoviesResponseMapper
) : SearchMoviesRepository {

    override fun searchMovies(
        query: String,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponse> {
        return moviesRemoteDataSource.searchMovies(query, page, language)
            .map { searchMoviesResponseMapper.mapFromEntity(it) }
    }

}