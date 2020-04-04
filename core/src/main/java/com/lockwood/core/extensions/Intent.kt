package com.lockwood.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent

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

fun Context.newIntent(className: String): Intent = Intent().apply {
    setClassName(packageName, className)
}

inline fun <reified T : Any> Context.newIntent(): Intent = Intent(this, T::class.java)