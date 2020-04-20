package com.lockwood.core.network.extensions

import okhttp3.HttpUrl
import okhttp3.Request

inline fun Request.edit(
    action: Request.Builder.() -> Request.Builder
): Request {
    return newBuilder()
        .action()
        .build()
}

inline fun Request.editUrl(
    action: HttpUrl.Builder.() -> HttpUrl.Builder
): Request {
    val url = url.newBuilder()
        .action()
        .build()

    return edit {
        url(url)
    }
}