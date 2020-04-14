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
            val genreModels = genreModels.map {
                MovieResponseEntity.GenreEntity(
                    id = it.id,
                    name = it.name
                )
            }
            return MovieResponseEntity(
                adult = adult,
                backdrop = backdrop ?: String.EMPTY,
                budget = budget,
                genreModels = genreModels,
                homepage = homepage,
                id = id,
                imdbId = imdbId,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                overview = overview,
                popularity = popularity,
                poster = poster ?: String.EMPTY,
                releaseDate = releaseDate,
                revenue = revenue,
                runtime = runtime,
                status = status,
                tagline = tagline,
                title = title,
                video = video,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }
    }

    override fun mapToRemote(type: MovieResponseEntity): MovieResponseModel {
        with(type) {
            val genreModels = genreModels.map {
                MovieResponseModel.GenreModel(
                    id = it.id,
                    name = it.name
                )
            }
            return MovieResponseModel(
                adult = adult,
                backdrop = backdrop,
                budget = budget,
                genreModels = genreModels,
                homepage = homepage,
                id = id,
                imdbId = imdbId,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                overview = overview,
                popularity = popularity,
                poster = poster,
                releaseDate = releaseDate,
                revenue = revenue,
                runtime = runtime,
                status = status,
                tagline = tagline,
                title = title,
                video = video,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }
    }

}