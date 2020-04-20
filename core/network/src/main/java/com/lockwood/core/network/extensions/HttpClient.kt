package com.lockwood.core.network.extensions

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

inline fun OkHttpClient.edit(
    action: OkHttpClient.Builder.() -> OkHttpClient.Builder
): OkHttpClient {
    return newBuilder()
        .action()
        .build()
}

fun OkHttpClient.Builder.commonTimeout(
    timeout: Long, unit: TimeUnit
): OkHttpClient.Builder {
    connectTimeout(timeout, unit)
        .readTimeout(timeout, unit)
        .writeTimeout(timeout, unit)
    return this
}

fun OkHttpClient.Builder.addInterceptors(
    vararg interceptors: Interceptor
): OkHttpClient.Builder {
    interceptors.forEach {
        addInterceptor(it)
    }
    return this
}