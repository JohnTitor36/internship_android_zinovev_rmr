package com.lockwood.themoviedb.movies.data.mapper

import com.lockwood.core.mapper.Mapper
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.domain.model.SearchMoviesResponse
import javax.inject.Inject

class SearchMoviesResponseMapper @Inject constructor() :
    Mapper<SearchMoviesResponseEntity, SearchMoviesResponse> {

    override fun mapFromEntity(type: SearchMoviesResponseEntity): SearchMoviesResponse {
        val results = type.results.map {
            with(it) {
                SearchMoviesResponse.Result(
                    popularity = popularity,
                    voteCount = voteCount,
                    video = video,
                    poster = poster,
                    id = id,
                    adult = adult,
                    backdrop = backdrop,
                    originalLanguage = originalLanguage,
                    originalTitle = originalTitle,
                    genreIds = genreIds,
                    title = title,
                    voteAverage = voteAverage,
                    overview = overview,
                    releaseDate = releaseDate
                )
            }
        }
        return SearchMoviesResponse(
            page = type.page,
            totalResults = type.totalResults,
            totalPages = type.totalPages,
            results = results
        )
    }

    override fun mapToEntity(type: SearchMoviesResponse): SearchMoviesResponseEntity {
        val results = type.results.map {
            with(it) {
                SearchMoviesResponseEntity.Result(
                    popularity = popularity,
                    voteCount = voteCount,
                    video = video,
                    poster = poster,
                    id = id,
                    adult = adult,
                    backdrop = backdrop,
                    originalLanguage = originalLanguage,
                    originalTitle = originalTitle,
                    genreIds = genreIds,
                    title = title,
                    voteAverage = voteAverage,
                    overview = overview,
                    releaseDate = releaseDate
                )
            }
        }
        return SearchMoviesResponseEntity(
            page = type.page,
            totalResults = type.totalResults,
            totalPages = type.totalPages,
            results = results
        )
    }

}