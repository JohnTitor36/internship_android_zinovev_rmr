package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.data.GravatarUrl
import com.lockwood.core.utils.GravatarUtils
import com.lockwood.core.window.WindowManager
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class GravatarUrlAdapter(private val windowManager: WindowManager) {

    @ToJson
    fun toJson(gravatarUrl: GravatarUrl): String {
        return GravatarUtils.gravatarUrlToHash(gravatarUrl.value)
    }

    @FromJson
    fun fromJson(hash: String): GravatarUrl {
        val imageSize = windowManager.screenWidth / 4
        val url = GravatarUtils.hashToGravatarUrl(hash, imageSize)
        return GravatarUrl(url)
    }

}