package com.lockwood.core.network.moshi.qualifier

import com.squareup.moshi.JsonQualifier

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class GravatarUrl

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class PosterUrl

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class BackdropUrl