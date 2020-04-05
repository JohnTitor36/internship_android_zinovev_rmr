package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.data.PosterUrl
import com.lockwood.core.utils.PosterUtils
import com.lockwood.core.window.WindowManager
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class PosterAdapter(private val windowManager: WindowManager) {

    @ToJson
    fun toJson(imageUrl: PosterUrl): String {
        return PosterUtils.posterUrlToPath(imageUrl.value)
    }

    @FromJson
    fun fromJson(path: String): PosterUrl {
        val imageSize = windowManager.screenWidth / 4
        val url = PosterUtils.posterPathToUrl(path, imageSize)
        return PosterUrl(url)
    }

}