package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.network.moshi.qualifier.BackdropUrl
import com.lockwood.core.network.utils.image.BackdropUtils
import com.lockwood.core.window.WindowManager
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackdropAdapter @Inject constructor(
    private val windowManager: WindowManager,
    private val backdropUtils: BackdropUtils
) {

    @ToJson
    fun toJson(@BackdropUrl imageUrl: String): String {
        return backdropUtils.backdropUrlToPath(imageUrl)
    }

    @FromJson
    @BackdropUrl
    fun fromJson(path: String): String {
        val imageSize = windowManager.screenWidth shr 2
        return backdropUtils.backdropPathToUrl(path, imageSize)
    }

}