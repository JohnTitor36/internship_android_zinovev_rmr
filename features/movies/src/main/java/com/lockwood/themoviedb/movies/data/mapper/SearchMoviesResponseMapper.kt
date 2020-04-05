package com.lockwood.themoviedb.movies.data.mapper

import com.lockwood.core.mapper.Mapper
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.domain.model.SearchMoviesResponse
import javax.inject.Inject

class SearchMoviesResponseMapper @Inject constructor() :
    Mapper<SearchMoviesResponseEntity, SearchMoviesResponse> {

    override fun mapFromEntity(type: SearchMoviesResponseEntity): SearchMoviesResponse {
        val results = type.results.map {
            with(it){
                SearchMoviesResponse.Result(
                    popularity,
                    voteCount,
                    video,
                    posterPath,
                    id,
                    adult,
                    backdropPath,
                    originalLanguage,
                    originalTitle,
                    genreIds,
                    title,
                    voteAverage,
                    overview,
                    releaseDate
                )
            }
        }
        return SearchMoviesResponse(type.page, type.totalResults, type.totalPages, results)
    }

    override fun mapToEntity(type: SearchMoviesResponse): SearchMoviesResponseEntity {
        val results = type.results.map {
            with(it){
                SearchMoviesResponseEntity.Result(
                    popularity,
                    voteCount,
                    video,
                    posterPath,
                    id,
                    adult,
                    backdropPath,
                    originalLanguage,
                    originalTitle,
                    genreIds,
                    title,
                    voteAverage,
                    overview,
                    releaseDate
                )
            }
        }
        return SearchMoviesResponseEntity(type.page, type.totalResults, type.totalPages, results)
    }

}