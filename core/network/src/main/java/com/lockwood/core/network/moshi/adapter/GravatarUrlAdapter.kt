package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.network.moshi.qualifier.GravatarUrl
import com.lockwood.core.utils.GravatarUtils
import com.lockwood.core.window.WindowManager
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class GravatarUrlAdapter(private val windowManager: WindowManager) {

    @ToJson
    fun toJson(@GravatarUrl gravatarUrl: String): String {
        return GravatarUtils.gravatarUrlToHash(gravatarUrl)
    }

    @FromJson
    @GravatarUrl
    fun fromJson(hash: String): String {
        val imageSize = windowManager.screenWidth / 4
        return GravatarUtils.hashToGravatarUrl(hash, imageSize)
    }

}