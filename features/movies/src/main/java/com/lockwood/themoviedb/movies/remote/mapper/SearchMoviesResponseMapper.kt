package com.lockwood.themoviedb.movies.remote.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import com.lockwood.themoviedb.movies.remote.model.response.SearchMoviesResponseModel
import javax.inject.Inject

class SearchMoviesResponseMapper @Inject constructor() :
    EntityRemoteMapper<SearchMoviesResponseModel, SearchMoviesResponseEntity> {

    override fun mapFromRemote(type: SearchMoviesResponseModel): SearchMoviesResponseEntity {
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

    override fun mapToRemote(type: SearchMoviesResponseEntity): SearchMoviesResponseModel {
        val results = type.results.map {
            SearchMoviesResponseModel.Result(
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
        return SearchMoviesResponseModel(type.page, type.totalResults, type.totalPages, results)
    }

}