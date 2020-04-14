package com.lockwood.themoviedb.movies.data.mapper

import com.lockwood.core.mapper.Mapper
import com.lockwood.themoviedb.movies.data.model.MovieResponseEntity
import com.lockwood.themoviedb.movies.domain.model.MovieResponse
import javax.inject.Inject

class MovieResponseMapper @Inject constructor() :
    Mapper<MovieResponseEntity, MovieResponse> {

    override fun mapFromEntity(type: MovieResponseEntity): MovieResponse {
        with(type) {
            val genreModels = genreModels.map {
                MovieResponse.Genre(
                    id = it.id,
                    name = it.name
                )
            }
            return MovieResponse(
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

    override fun mapToEntity(type: MovieResponse): MovieResponseEntity {
        with(type) {
            val genreModels = genreModels.map {
                MovieResponseEntity.GenreEntity(
                    id = it.id,
                    name = it.name
                )
            }
            return MovieResponseEntity(
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