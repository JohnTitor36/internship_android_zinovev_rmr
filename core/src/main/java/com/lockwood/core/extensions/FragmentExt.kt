package com.lockwood.core.extensions

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

val Fragment.ctx: Context
    get() = requireContext()

inline fun <reified T : Fragment> newInstance(
    init: Bundle.() -> Unit = {}
): T {
    val fragment = T::class.java.newInstance()
    val args = Bundle()
    args.init()
    fragment.arguments = args
    return fragment
}