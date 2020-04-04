package com.lockwood.core.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes res: Int): Int = ContextCompat.getColor(this, res)

fun Context.string(@StringRes res: Int): String = getString(res)

fun Context.dimenPx(@DimenRes res: Int) = resources.getDimensionPixelSize(res)

fun Context.drawable(@DrawableRes res: Int): Drawable? = ContextCompat.getDrawable(this, res)