package com.lockwood.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

fun Context.color(@ColorRes res: Int): Int = ContextCompat.getColor(this, res)

fun Context.font(@FontRes res: Int): Typeface? = ResourcesCompat.getFont(this, res)

inline fun <reified T : Activity> Context.launchActivity(
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(Intent(intent).apply(init))
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)