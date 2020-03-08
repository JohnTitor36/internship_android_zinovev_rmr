package com.lockwood.themoviedb.glide

import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideType

@GlideExtension
object GlideExtension {

    @JvmStatic
    @GlideType(PictureDrawable::class)
    fun asSvg(requestBuilder: RequestBuilder<PictureDrawable>): RequestBuilder<PictureDrawable> {
        return requestBuilder.addListener(SvgSoftwareLayerSetter())
    }

}