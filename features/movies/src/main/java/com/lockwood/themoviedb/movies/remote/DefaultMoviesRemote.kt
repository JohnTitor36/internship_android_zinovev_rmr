package com.lockwood.themoviedb.movies.remote

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.data.repository.MoviesRemote
import com.lockwood.themoviedb.movies.remote.mapper.SearchMoviesResponseMapper
import io.reactivex.Observable
import javax.inject.Inject

@FeatureScope
class DefaultMoviesRemote @Inject constructor(
    private val moviesService: MoviesService,
    private val searchMoviesResponseMapper: SearchMoviesResponseMapper
) : MoviesRemote {

    override fun searchMovies(
        query: String,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponseEntity> {
        return moviesService.searchMovies(query, page, language)
            .map { searchMoviesResponseMapper.mapFromRemote(it) }
    }


}
