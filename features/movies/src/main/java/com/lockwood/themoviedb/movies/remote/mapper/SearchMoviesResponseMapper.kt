package com.lockwood.themoviedb.movies.remote.mapper

import com.lockwood.core.extensions.EMPTY
import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.remote.model.response.SearchMoviesResponseModel
import javax.inject.Inject

class SearchMoviesResponseMapper @Inject constructor() :
    EntityRemoteMapper<SearchMoviesResponseModel, SearchMoviesResponseEntity> {

    override fun mapFromRemote(type: SearchMoviesResponseModel): SearchMoviesResponseEntity {
        val results = type.results.map {
            with(it) {
                SearchMoviesResponseEntity.Result(
                    popularity = popularity,
                    voteCount = voteCount,
                    video = video,
                    poster = poster ?: String.EMPTY,
                    id = id,
                    adult = adult,
                    backdrop = backdrop ?: String.EMPTY,
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

    override fun mapToRemote(type: SearchMoviesResponseEntity): SearchMoviesResponseModel {
        val results = type.results.map {
            with(it) {
                SearchMoviesResponseModel.Result(
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
        return SearchMoviesResponseModel(
            page = type.page,
            totalResults = type.totalResults,
            totalPages = type.totalPages,
            results = results
        )
    }

}