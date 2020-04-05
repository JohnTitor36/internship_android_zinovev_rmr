package com.lockwood.core.utils

import androidx.annotation.Px

object GravatarUtils {

    private const val GRAVATAR_IMAGE_BASE_URL = "https://www.gravatar.com/avatar/"
    private const val GRAVATAR_IMAGE_SIZE = "?s="

    // https://www.gravatar.com/avatar/8uO0gUM8aNqYLs1OsTBQiXu0fEv?s=500
    fun hashToGravatarUrl(hash: String, @Px size: Int): String {
        return StringBuilder()
            .append("$GRAVATAR_IMAGE_BASE_URL$hash")
            .append("$GRAVATAR_IMAGE_SIZE$size")
            .toString()
    }

    // 8uO0gUM8aNqYLs1OsTBQiXu0fEv
    fun gravatarUrlToHash(url: String): String {
        val avatar = "/avatar/"
        val startIndex = url.indexOf(avatar) + avatar.length
        val endIndex = url.indexOf(GRAVATAR_IMAGE_SIZE) - 1
        return url.substring(startIndex, endIndex)
    }

}

