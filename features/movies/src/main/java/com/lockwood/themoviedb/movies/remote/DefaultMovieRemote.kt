package com.lockwood.themoviedb.movies.remote

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.model.MovieResponseEntity
import com.lockwood.themoviedb.movies.data.repository.MovieRemote
import com.lockwood.themoviedb.movies.remote.mapper.MovieResponseMapper
import com.lockwood.themoviedb.movies.remote.service.MovieService
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class DefaultMovieRemote @Inject constructor(
    private val movieService: MovieService,
    private val movieResponseMapper: MovieResponseMapper
) : MovieRemote {

    override fun loadMovie(movieId: Int, language: String): Single<MovieResponseEntity> {
        return movieService.loadMovie(movieId, language)
            .map { movieResponseMapper.mapFromRemote(it) }
    }
}
