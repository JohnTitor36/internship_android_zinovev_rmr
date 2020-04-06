package com.lockwood.themoviedb.movies.remote.mapper

import com.lockwood.core.data.BackdropUrl
import com.lockwood.core.data.PosterUrl
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
                    popularity,
                    voteCount,
                    video,
                    poster.value,
                    id,
                    adult,
                    backdrop.value,
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

    override fun mapToRemote(type: SearchMoviesResponseEntity): SearchMoviesResponseModel {
        val results = type.results.map {
            with(it) {
                SearchMoviesResponseModel.Result(
                    popularity,
                    voteCount,
                    video,
                    PosterUrl(poster),
                    id,
                    adult,
                    BackdropUrl(backdrop),
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
        return SearchMoviesResponseModel(type.page, type.totalResults, type.totalPages, results)
    }

}