package com.lockwood.core.reader

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.lockwood.core.extensions.color
import com.lockwood.core.extensions.dimenInPx
import com.lockwood.core.extensions.string
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceReader @Inject constructor(
    private val context: Context
) {

    fun color(@ColorRes res: Int): Int = context.color(res)

    fun string(@StringRes res: Int): String = context.string(res)

    fun dimenInPx(@DimenRes res: Int) = context.dimenInPx(res)

}