package com.lockwood.core.utils

object ApiImageUtils {

    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"

    // https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun imagePathToUrl(path: String, width: String): String {
        return "$IMAGE_BASE_URL/$width$path"
    }

    // 8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun imageUrlToPath(url: String): String {
        val startIndex = url.lastIndexOf("/")
        val endIndex = url.length
        return url.substring(startIndex, endIndex)
    }

}