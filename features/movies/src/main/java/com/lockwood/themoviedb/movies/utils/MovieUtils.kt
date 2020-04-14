package com.lockwood.themoviedb.movies.utils

import com.lockwood.core.extensions.toYear
import com.lockwood.core.reader.ResourceReader
import com.lockwood.themoviedb.movies.R
import java.util.*

object MovieUtils {

    fun buildOriginalTitle(
        resourceReader: ResourceReader,
        title: String,
        releaseDate: Date
    ): String {
        val resultTitle = resourceReader.string(R.string.title_movie_with_year)
        val movieYear = releaseDate.toYear()
        return resultTitle.format(title, movieYear)
    }

    fun buildDurationTitle(
        resourceReader: ResourceReader,
        runtime: Int
    ): String {
        val resultTitle = resourceReader.string(R.string.title_movie_runtime)
        return resultTitle.format(runtime)
    }

    fun ratingToColor(
        resourceReader: ResourceReader,
        rating: Double
    ): Int {
        val colorRes = when (rating.toInt()) {
            in 10 downTo 8 -> R.color.movie_rating_good
            in 9 downTo 7 -> R.color.movie_rating_normal
            in 6 downTo 4 -> R.color.movie_rating_not_so_good
            else -> R.color.movie_rating_bad
        }
        return resourceReader.color(colorRes)
    }

}