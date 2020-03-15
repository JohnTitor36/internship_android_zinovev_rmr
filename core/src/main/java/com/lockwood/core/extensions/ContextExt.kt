package com.lockwood.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes res: Int): Int = ContextCompat.getColor(this, res)

inline fun <reified T : Activity> Context.launchActivity(
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(Intent(intent).apply(init))
}

inline fun newIntent(
    context: Context,
    className: String,
    init: Intent.() -> Unit = {}
): Intent {
    val packageName = context.packageName
    val packageNameWithoutSuffix = packageName.removeSuffix(DEBUG_SUFFIX)
    val resultClassName = "$packageNameWithoutSuffix$className"

    return Intent().apply {
        setClassName(packageName, resultClassName)
        init()
    }
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

const val DEBUG_SUFFIX = ".debug"