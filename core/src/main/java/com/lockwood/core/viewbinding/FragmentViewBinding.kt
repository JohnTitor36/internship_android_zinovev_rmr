package com.lockwood.core.viewbinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <reified T : ViewBinding> createView(
    inflater: LayoutInflater,
    container: ViewGroup?
): View {
    return inflater.inflateViewBinding<T>(container, false).root
}