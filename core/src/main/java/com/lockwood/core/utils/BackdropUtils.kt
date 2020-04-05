package com.lockwood.core.utils

import androidx.annotation.Px

object BackdropUtils {

    private const val ORIGINAL_SIZE = "original"
    private const val IMAGE_SIZE_LIMIT = 1560

    private val sizes = arrayOf(300, 780, 1280)

    // https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun backdropPathToUrl(path: String, @Px width: Int): String {
        val size = if (width >= IMAGE_SIZE_LIMIT) {
            ORIGINAL_SIZE
        } else {
            val widthPath = sizes.firstOrNull { it >= width } ?: sizes.last()
            "w$widthPath"
        }
        return ApiImageUtils.imagePathToUrl(path, size)
    }

    // 8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun backdropUrlToPath(url: String): String {
        return ApiImageUtils.imageUrlToPath(url)
    }

}