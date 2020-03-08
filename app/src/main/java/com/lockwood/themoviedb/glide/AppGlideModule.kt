package com.lockwood.themoviedb.glide

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.caverock.androidsvg.SVG
import java.io.InputStream

@GlideModule
class AppGlideModule : AppGlideModule() {

    override fun applyOptions(
        context: Context,
        builder: GlideBuilder
    ) {
        super.applyOptions(context, builder)
        // Creates an DiskLruCache based disk cache in the external disk cache directory,
        // which falls back to the internal disk cache if no external storage is available
        builder.setDiskCache(ExternalPreferredCacheDiskCacheFactory(context))
        // enable hardware Bitmaps
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
    }

    override fun registerComponents(
        context: Context,
        glide: Glide,
        registry: Registry
    ) {
        registry.register(
            SVG::class.java, PictureDrawable::class.java,
            SvgDrawableTranscoder()
        ).append(InputStream::class.java, SVG::class.java, SvgDecoder())
    }

    // Disable manifest parsing to avoid adding similar modules twice.
    override fun isManifestParsingEnabled() = false

}