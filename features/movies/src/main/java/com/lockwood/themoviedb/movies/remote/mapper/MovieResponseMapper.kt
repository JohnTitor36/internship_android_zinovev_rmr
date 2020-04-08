package com.lockwood.themoviedb.movies.remote.mapper

import com.lockwood.core.extensions.EMPTY
import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.movies.data.model.MovieResponseEntity
import com.lockwood.themoviedb.movies.remote.model.response.MovieResponseModel
import javax.inject.Inject

class MovieResponseMapper @Inject constructor() :
    EntityRemoteMapper<MovieResponseModel, MovieResponseEntity> {

    override fun mapFromRemote(type: MovieResponseModel): MovieResponseEntity {
        with(type) {
            val genreModels = genreModels.map { MovieResponseEntity.GenreEntity(it.id, it.name) }
            return MovieResponseEntity(
                adult,
                backdrop ?: String.EMPTY,
                budget,
                genreModels,
                homepage,
                id,
                imdbId,
                originalLanguage,
                originalTitle,
                overview,
                popularity,
                poster ?: String.EMPTY,
                releaseDate,
                revenue,
                runtime,
                status,
                tagline,
                title,
                video,
                voteAverage,
                voteCount
            )
        }
    }

    override fun mapToRemote(type: MovieResponseEntity): MovieResponseModel {
        with(type) {
            val genreModels = genreModels.map { MovieResponseModel.GenreModel(it.id, it.name) }
            return MovieResponseModel(
                adult,
                backdrop,
                budget,
                genreModels,
                homepage,
                id,
                imdbId,
                originalLanguage,
                originalTitle,
                overview,
                popularity,
                poster,
                releaseDate,
                revenue,
                runtime,
                status,
                tagline,
                title,
                video,
                voteAverage,
                voteCount
            )
        }
    }

}