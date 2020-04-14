package com.lockwood.test.extensions

fun catchException(method: () -> Unit): Exception? {
    var exception: Exception? = null

    try {
        method.invoke()
    } catch (e: Exception) {
        exception = e
    }

    return exception
}