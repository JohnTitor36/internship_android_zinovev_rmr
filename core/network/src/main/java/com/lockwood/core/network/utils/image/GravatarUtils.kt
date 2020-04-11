package com.lockwood.core.network.utils.image

import androidx.annotation.Px
import com.lockwood.core.network.R

import com.lockwood.core.reader.ResourceReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GravatarUtils @Inject constructor(
    private val resourceReader: ResourceReader
) {

    companion object {

        private const val GRAVATAR_HASH_REGEX = "(?<=avatar/)(.*)(?=\\?)"
    }

    // https://www.gravatar.com/avatar/8uO0gUM8aNqYLs1OsTBQiXu0fEv?s=500
    fun hashToGravatarUrl(hash: String, @Px size: Int): String {
        val imageUrl = resourceReader.string(R.string.gravatar_url)
        return imageUrl.format(hash, size)
    }

    // 8uO0gUM8aNqYLs1OsTBQiXu0fEv
    fun gravatarUrlToHash(url: String): String {
        val regex = GRAVATAR_HASH_REGEX.toRegex()
        val hashMatchResult = requireNotNull(regex.find(url))
        return hashMatchResult.value
    }

}

