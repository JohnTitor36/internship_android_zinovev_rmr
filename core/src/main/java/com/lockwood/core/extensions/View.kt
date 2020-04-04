package com.lockwood.core.extensions

import android.view.View

inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener {
        if (measuredWidth > 0 && measuredHeight > 0) {
            f()
        }
    }
}
