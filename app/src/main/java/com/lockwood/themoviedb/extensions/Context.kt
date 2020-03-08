package com.lockwood.themoviedb.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> Context.launchActivity(
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(Intent(intent).apply(init))
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)