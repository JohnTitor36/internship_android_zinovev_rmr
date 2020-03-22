package com.lockwood.core.extensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes res: Int): Int = ContextCompat.getColor(this, res)

fun Context.dimenInPx(@DimenRes dimenRes: Int) = resources.getDimensionPixelSize(dimenRes)