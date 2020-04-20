package com.lockwood.core.network.extensions

import retrofit2.Retrofit

inline fun Retrofit.edit(
    action: Retrofit.Builder.() -> Retrofit.Builder
): Retrofit {
    return newBuilder()
        .action()
        .build()
}