package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.data.BackdropUrl
import com.lockwood.core.utils.BackdropUtils
import com.lockwood.core.window.WindowManager
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class BackdropAdapter(private val windowManager: WindowManager) {

    @ToJson
    fun toJson(imageUrl: BackdropUrl): String {
        return BackdropUtils.backdropUrlToPath(imageUrl.value)
    }

    @FromJson
    fun fromJson(path: String): BackdropUrl {
        val imageSize = windowManager.screenWidth / 4
        val url = BackdropUtils.backdropPathToUrl(path, imageSize)
        return BackdropUrl(url)
    }

}