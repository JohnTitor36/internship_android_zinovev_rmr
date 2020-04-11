package com.lockwood.core.network.utils.image

import androidx.annotation.Px
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackdropUtils @Inject constructor(
    private val apiImageUtils: ApiImageUtils
) {

    companion object {

        private const val IMAGE_SIZE_LIMIT = 1000

        private val AVAILABLE_EXACT_IMAGE_SIZES = arrayOf(300, 780, 1280)
    }

    // https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun backdropPathToUrl(path: String, @Px width: Int): String {
        val size = apiImageUtils.findOptimalImageSize(
            width = width,
            sizeToUseOriginal = IMAGE_SIZE_LIMIT,
            availableSizes = AVAILABLE_EXACT_IMAGE_SIZES
        )
        return apiImageUtils.imagePathToUrl(path, size)
    }

    // 8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
    fun backdropUrlToPath(url: String): String {
        return apiImageUtils.imageUrlToPath(url)
    }

}