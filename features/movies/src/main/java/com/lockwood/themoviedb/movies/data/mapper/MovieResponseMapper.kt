package com.lockwood.themoviedb.movies.data.mapper

import com.lockwood.core.mapper.Mapper
import com.lockwood.themoviedb.movies.data.model.MovieResponseEntity
import com.lockwood.themoviedb.movies.domain.model.MovieResponse
import javax.inject.Inject

class MovieResponseMapper @Inject constructor() :
    Mapper<MovieResponseEntity, MovieResponse> {

    override fun mapFromEntity(type: MovieResponseEntity): MovieResponse {
        with(type) {
            val genreModels = genreModels.map { MovieResponse.Genre(it.id, it.name) }
            return MovieResponse(
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

    override fun mapToEntity(type: MovieResponse): MovieResponseEntity {
        with(type) {
            val genreModels = genreModels.map { MovieResponseEntity.GenreEntity(it.id, it.name) }
            return MovieResponseEntity(
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