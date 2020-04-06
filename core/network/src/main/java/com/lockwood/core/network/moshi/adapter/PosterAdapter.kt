package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.network.moshi.qualifier.PosterUrl
import com.lockwood.core.utils.PosterUtils
import com.lockwood.core.window.WindowManager
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class PosterAdapter(private val windowManager: WindowManager) {

    @ToJson
    fun toJson(@PosterUrl imageUrl: String): String {
        return PosterUtils.posterUrlToPath(imageUrl)
    }

    @FromJson
    @PosterUrl
    fun fromJson(path: String): String {
        val imageSize = windowManager.screenWidth / 4
        return PosterUtils.posterPathToUrl(path, imageSize)
    }

}