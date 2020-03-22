package com.lockwood.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent

const val DEBUG_SUFFIX = ".debug"

inline fun <reified T : Activity> Context.launchActivity(
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>()
    intent.init()
    startActivity(Intent(intent).apply(init))
}

inline fun Context.launchActivity(
    className: String,
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent(className)
    intent.init()
    startActivity(Intent(intent).apply(init))
}

fun Context.newIntent(
    className: String
): Intent {
    val packageNameWithoutSuffix = packageName.removeSuffix(DEBUG_SUFFIX)
    val resultClassName = "$packageNameWithoutSuffix$className"
    return Intent().apply {
        setClassName(packageName, resultClassName)
    }
}

inline fun <reified T : Any> Context.newIntent(): Intent = Intent(this, T::class.java)