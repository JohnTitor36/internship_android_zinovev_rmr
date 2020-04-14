package com.lockwood.themoviedb.movies.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.lockwood.core.reader.ResourceReader
import com.lockwood.glide.extensions.drawableRequest
import com.lockwood.themoviedb.movies.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

fun Context.loadMoviePosterRequest(): RequestBuilder<Drawable> {
    val resourceReader = ResourceReader(this)
    val placeholder = resourceReader.drawable(R.drawable.ic_poster_placeholder)

    val roundedCornersTransformation = RoundedCornersTransformation(
        resourceReader.dimenInPx(R.dimen.item_movies_corner_radius),
        0,
        RoundedCornersTransformation.CornerType.ALL
    )

    return drawableRequest(
        resourceReader = resourceReader,
        placeholder = placeholder,
        fallback = placeholder,
        error = placeholder
    ).apply(RequestOptions.bitmapTransform(roundedCornersTransformation))
}