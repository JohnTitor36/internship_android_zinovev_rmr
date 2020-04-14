package com.lockwood.core.viewbinding

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <reified T : ViewBinding> ViewGroup.inflateItemViewBinding(): T {
    return inflateViewBinding(attachToRoot = false)
}