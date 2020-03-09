package com.lockwood.core.extensions

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

val Fragment.ctx: Context
    get() = requireContext()

fun Fragment.hideKeyboard() {
    val view = view?.rootView
    view?.let {
        val imm =
            context?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
        it.clearFocus()
    }
}

inline fun <reified T : Fragment> newFragment(
    init: Bundle.() -> Unit = {}
): T {
    val fragment = T::class.java.newInstance()
    val args = Bundle()
    args.init()
    fragment.arguments = args
    return fragment
}