package com.lockwood.themoviedb.movies.data

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.mapper.MovieResponseMapper
import com.lockwood.themoviedb.movies.data.source.MovieRemoteDataSource
import com.lockwood.themoviedb.movies.domain.model.MovieResponse
import com.lockwood.themoviedb.movies.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class DefaultMovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieResponseMapper: MovieResponseMapper
) : MovieRepository {

    override fun loadMovie(movieId: Int, language: String): Single<MovieResponse> {
        return movieRemoteDataSource.loadMovie(movieId, language)
            .map { movieResponseMapper.mapFromEntity(it) }
    }

}