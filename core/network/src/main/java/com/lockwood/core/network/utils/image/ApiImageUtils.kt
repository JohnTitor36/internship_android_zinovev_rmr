package com.lockwood.core.network.utils.image

import androidx.annotation.Px
import com.lockwood.core.network.R
import com.lockwood.core.reader.ResourceReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiImageUtils @Inject constructor(
    private val resourceReader: ResourceReader
) {

    companion object {

        private const val ORIGINAL_SIZE = "original"
    }

    // https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun imagePathToUrl(path: String, width: String): String {
        val imageUrl = resourceReader.string(R.string.tmdb_image_url)
        return imageUrl.format(width, path)
    }

    // 8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun imageUrlToPath(url: String): String {
        val startIndex = url.lastIndexOf("/")
        val endIndex = url.length
        return url.substring(startIndex, endIndex)
    }

    fun findOptimalImageSize(@Px width: Int, sizeToUseOriginal: Int, availableSizes: Array<Int>): String {
        return if (width >= sizeToUseOriginal) {
            ORIGINAL_SIZE
        } else {
            val widthPath = availableSizes.firstOrNull { it >= width } ?: availableSizes.last()
            "w$widthPath"
        }
    }

}