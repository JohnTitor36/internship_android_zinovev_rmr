package com.lockwood.themoviedb.movies.data.mapper

import com.lockwood.core.mapper.Mapper
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.domain.model.SearchMoviesResponse
import javax.inject.Inject

class SearchMoviesResponseMapper @Inject constructor() :
    Mapper<SearchMoviesResponseEntity, SearchMoviesResponse> {

    override fun mapFromEntity(type: SearchMoviesResponseEntity): SearchMoviesResponse {
        val results = type.results.map {
            SearchMoviesResponse.Result(
                it.popularity,
                it.voteCount,
                it.video,
                it.posterPath,
                it.id,
                it.adult,
                it.backdropPath,
                it.originalLanguage,
                it.originalTitle,
                it.genreIds,
                it.title,
                it.voteAverage,
                it.overview,
                it.releaseDate
            )
        }
        return SearchMoviesResponse(type.page, type.totalResults, type.totalPages, results)
    }

    override fun mapToEntity(type: SearchMoviesResponse): SearchMoviesResponseEntity {
        val results = type.results.map {
            SearchMoviesResponseEntity.Result(
                it.popularity,
                it.voteCount,
                it.video,
                it.posterPath,
                it.id,
                it.adult,
                it.backdropPath,
                it.originalLanguage,
                it.originalTitle,
                it.genreIds,
                it.title,
                it.voteAverage,
                it.overview,
                it.releaseDate
            )
        }
        return SearchMoviesResponseEntity(type.page, type.totalResults, type.totalPages, results)
    }

}