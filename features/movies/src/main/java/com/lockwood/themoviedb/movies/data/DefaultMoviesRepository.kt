package com.lockwood.themoviedb.movies.data

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.mapper.SearchMoviesResponseMapper
import com.lockwood.themoviedb.movies.data.source.MoviesRemoteDataSource
import com.lockwood.themoviedb.movies.domain.model.SearchMoviesResponse
import com.lockwood.themoviedb.movies.domain.repository.MoviesRepository
import io.reactivex.Observable
import javax.inject.Inject

@FeatureScope
class DefaultMoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val searchMoviesResponseMapper: SearchMoviesResponseMapper
) : MoviesRepository {

    override fun searchMovies(
        query: String,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponse> {
        return moviesRemoteDataSource.searchMovies(query, page, language)
            .map { searchMoviesResponseMapper.mapFromEntity(it) }
    }

}