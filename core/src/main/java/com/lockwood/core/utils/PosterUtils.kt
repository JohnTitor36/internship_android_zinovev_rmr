package com.lockwood.core.utils

import androidx.annotation.Px

object PosterUtils {

    private const val ORIGINAL_SIZE = "original"
    private const val IMAGE_SIZE_LIMIT = 1000

    private val sizes = arrayOf(92, 154, 185, 342, 500, 780)

    // https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun posterPathToUrl(path: String?, @Px width: Int): String {
        val size = if (width >= IMAGE_SIZE_LIMIT) {
            ORIGINAL_SIZE
        } else {
            val widthPath = sizes.firstOrNull { it >= width } ?: sizes.last()
            "w$widthPath"
        }
        return ApiImageUtils.imagePathToUrl(path, size)
    }

    // 8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun posterUrlToPath(url: String): String {
        return ApiImageUtils.imageUrlToPath(url)
    }

}