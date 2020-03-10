package com.lockwood.core.extensions

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

val Fragment.ctx: Context
    get() = requireContext()

val Fragment.fragmentActivity: FragmentActivity
    get() = requireActivity()

inline fun <reified T : Fragment> newFragment(
    init: Bundle.() -> Unit = {}
): T {
    val fragment = T::class.java.newInstance()
    val args = Bundle()
    args.init()
    fragment.arguments = args
    return fragment
}