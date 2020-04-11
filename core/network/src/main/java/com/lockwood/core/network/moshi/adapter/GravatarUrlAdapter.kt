package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.network.moshi.qualifier.GravatarUrl
import com.lockwood.core.network.utils.image.GravatarUtils
import com.lockwood.core.window.WindowManager
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GravatarUrlAdapter @Inject constructor(
    private val windowManager: WindowManager,
    private val gravatarUtils: GravatarUtils
) {

    @ToJson
    fun toJson(@GravatarUrl gravatarUrl: String): String {
        return gravatarUtils.gravatarUrlToHash(gravatarUrl)
    }

    @FromJson
    @GravatarUrl
    fun fromJson(hash: String): String {
        val imageSize = windowManager.screenWidth shr 2
        return gravatarUtils.hashToGravatarUrl(hash, imageSize)
    }

}