package com.lockwood.core.network.moshi.qualifier

import com.squareup.moshi.JsonQualifier

@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GravatarUrl

@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class PosterUrl

@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BackdropUrl