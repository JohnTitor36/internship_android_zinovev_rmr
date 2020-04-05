package com.lockwood.core.utils

import androidx.annotation.Px
import com.lockwood.core.common.GravatarUrl

object GravatarUtils {

    private const val GRAVATAR_IMAGE_BASE_URL = "https://www.gravatar.com/avatar/"

    private const val GRAVATAR_IMAGE_SIZE = "?s="

    fun hashToGravatar(hash: String, @Px size: Int): GravatarUrl {
        val url = StringBuilder()
            .append("$GRAVATAR_IMAGE_BASE_URL$hash")
            .append("$GRAVATAR_IMAGE_SIZE$size")
            .toString()
        return GravatarUrl(url)
    }

    fun imageUrlToHash(url: String): String {
        val avatar = "/avatar/"
        val startIndex = url.indexOf(avatar) + avatar.length
        val endIndex = url.indexOf(GRAVATAR_IMAGE_SIZE) - 1
        return url.substring(startIndex, endIndex)
    }

}

