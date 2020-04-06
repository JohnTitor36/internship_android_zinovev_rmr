package com.lockwood.core.utils

object ApiImageUtils {

    private const val PLACE_HOLDER_IMAGE =
        "https://lh3.googleusercontent.com/proxy/83PlS4aaUgQUYFtx0TAPszZhlMAudVyVTEuLmCZoUnrQWcqYtohZh__62pj6ZqWZdGGmORVp94uYz1DB3Sh2f9p_EBoz3QcMrrWz"
    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"

    // https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun imagePathToUrl(path: String?, width: String): String {
        val imagePath = if (path.isNullOrEmpty()) {
            PLACE_HOLDER_IMAGE
        } else {
            path
        }
        return "$IMAGE_BASE_URL/$width$imagePath"
    }

    // 8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun imageUrlToPath(url: String): String {
        val startIndex = url.lastIndexOf("/")
        val endIndex = url.length
        return url.substring(startIndex, endIndex)
    }

    fun isPlaceholder(url: String): Boolean {
        return url.contains(PLACE_HOLDER_IMAGE)
    }

}