package com.lockwood.core.data

open class ImageUrl(val value: String)

class PosterUrl(value: String) : ImageUrl(value)

class BackdropUrl(value: String) : ImageUrl(value)