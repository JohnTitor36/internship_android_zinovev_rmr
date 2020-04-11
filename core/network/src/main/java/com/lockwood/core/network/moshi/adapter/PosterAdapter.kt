package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.network.moshi.qualifier.PosterUrl
import com.lockwood.core.network.utils.image.PosterUtils
import com.lockwood.core.window.WindowManager
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PosterAdapter @Inject constructor(
    private val windowManager: WindowManager,
    private val posterUtils: PosterUtils
) {

    @ToJson
    fun toJson(@PosterUrl imageUrl: String): String {
        return posterUtils.posterUrlToPath(imageUrl)
    }

    @FromJson
    @PosterUrl
    fun fromJson(path: String): String {
        val imageSize = windowManager.screenWidth shr 2
        return posterUtils.posterPathToUrl(path, imageSize)
    }

}