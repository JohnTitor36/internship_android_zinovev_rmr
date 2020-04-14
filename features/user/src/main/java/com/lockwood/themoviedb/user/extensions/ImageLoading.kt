package com.lockwood.themoviedb.user.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.lockwood.core.reader.ResourceReader
import com.lockwood.glide.extensions.drawableRequest
import com.lockwood.themoviedb.user.R
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation

fun Context.loadAvatarRequest(): RequestBuilder<Drawable> {
    val resourceReader = ResourceReader(this)
    val defaultAvatar = resourceReader.drawable(R.drawable.ic_avatar_default)

    val circleWithBorderTransformation = CropCircleWithBorderTransformation(
        resourceReader.dimenInPx(R.dimen.user_avatar_corners_size),
        resourceReader.color(R.color.avatar_corner)
    )
    return drawableRequest(
        resourceReader = resourceReader,
        placeholder = defaultAvatar,
        fallback = defaultAvatar,
        error = defaultAvatar
    ).apply(RequestOptions.bitmapTransform(circleWithBorderTransformation))
}