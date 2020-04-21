package com.lockwood.themoviedb.movies.remote

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.data.repository.SearchMoviesRemote
import com.lockwood.themoviedb.movies.remote.mapper.SearchMoviesResponseMapper
import com.lockwood.themoviedb.movies.remote.service.SearchMoviesService
import io.reactivex.Observable
import javax.inject.Inject

@FeatureScope
class DefaultSearchMoviesRemote @Inject constructor(
    private val moviesService: SearchMoviesService,
    private val searchMoviesResponseMapper: SearchMoviesResponseMapper
) : SearchMoviesRemote {

    override fun searchMovies(
        query: String,
        page: Int,
        language: String
    ): Observable<SearchMoviesResponseEntity> {
        return moviesService.searchMovies(query, page, language)
            .map { searchMoviesResponseMapper.mapFromRemote(it) }
    }


}
