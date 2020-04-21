package com.lockwood.themoviedb.movies.cache.mapper

import com.lockwood.core.mapper.EntityCacheMapper
import com.lockwood.themoviedb.movies.cache.model.SearchMoviesResponseDb
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import javax.inject.Inject

class SearchMoviesResponseMapper @Inject constructor() :
    EntityCacheMapper<SearchMoviesResponseDb, SearchMoviesResponseEntity> {

    override fun mapToCached(type: SearchMoviesResponseEntity): SearchMoviesResponseDb {
        val results = type.results.map {
            with(it) {
                SearchMoviesResponseDb.Result(
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
        return SearchMoviesResponseDb(
            page = type.page,
            totalResults = type.totalResults,
            totalPages = type.totalPages,
            results = results
        )
    }

    override fun mapFromCached(type: SearchMoviesResponseDb): SearchMoviesResponseEntity {
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