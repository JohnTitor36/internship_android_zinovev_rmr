package com.lockwood.glide.extensions

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.lockwood.core.reader.ResourceReader
import com.lockwood.glide.R

val ResourceReader.defaultPlaceholder
    get() = ColorDrawable(color(R.color.gray))

fun Context.drawableRequest(
    resourceReader: ResourceReader = ResourceReader(this),
    placeholder: Drawable? = resourceReader.defaultPlaceholder,
    fallback: Drawable? = placeholder,
    error: Drawable? = null
): RequestBuilder<Drawable> {
    return Glide.with(this)
        .asDrawable()
        .dontAnimate()
        .placeholder(placeholder)
        .fallback(fallback)
        .error(error)
}

fun ImageView.load(url: String, requestBuilder: RequestBuilder<Drawable>) {
    requestBuilder.load(url).into(this)
}