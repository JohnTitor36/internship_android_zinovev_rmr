package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.network.moshi.qualifier.BackdropUrl
import com.lockwood.core.utils.BackdropUtils
import com.lockwood.core.window.WindowManager
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class BackdropAdapter(private val windowManager: WindowManager) {

    @ToJson
    fun toJson(@BackdropUrl imageUrl: String): String {
        return BackdropUtils.backdropUrlToPath(imageUrl)
    }

    @FromJson
    @BackdropUrl
    fun fromJson(path: String): String {
        val imageSize = windowManager.screenWidth / 4
        return BackdropUtils.backdropPathToUrl(path, imageSize)
    }

}