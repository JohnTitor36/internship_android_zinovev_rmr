package com.lockwood.themoviedb.movies.cache.mapper

import com.lockwood.core.data.Language
import com.lockwood.core.extensions.toDate
import com.lockwood.core.extensions.toFormatString
import com.lockwood.core.mapper.EntityCacheMapper
import com.lockwood.core.network.moshi.adapter.DateAdapter.Companion.DEFAULT_DATE_FORMAT
import com.lockwood.themoviedb.movies.cache.model.SearchMoviesResponseDb
import com.lockwood.themoviedb.movies.data.model.SearchMoviesResponseEntity
import java.text.ParseException
import java.util.*
import javax.inject.Inject

class SearchMoviesResponseMapper @Inject constructor() :
    EntityCacheMapper<SearchMoviesResponseDb, SearchMoviesResponseEntity> {

    // TODO: Исправить toDate/toFormatString
    // TODO: Рефакторинг Language

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
                    originalLanguage = originalLanguage.value,
                    originalTitle = originalTitle,
                    genreIds = genreIds.joinToString(separator = ","),
                    title = title,
                    voteAverage = voteAverage,
                    overview = overview,
                    releaseDate = releaseDate.toFormatString(DEFAULT_DATE_FORMAT)
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
                    originalLanguage = Language.values()
                        .firstOrNull { language -> language.name == originalLanguage }
                        ?: Language.ENG,
                    originalTitle = originalTitle,
                    genreIds = genreIds.split(",").map { id -> id.toInt() },
                    title = title,
                    voteAverage = voteAverage,
                    overview = overview,
                    releaseDate = try {
                        releaseDate.toDate(DEFAULT_DATE_FORMAT)
                    } catch (e: ParseException) {
                        Date()
                    }
                )
            }
        }
        return SearchMoviesResponseEntity(
            page = 1,
            totalResults = results.size,
            totalPages = 1,
            results = results
        )
    }

}