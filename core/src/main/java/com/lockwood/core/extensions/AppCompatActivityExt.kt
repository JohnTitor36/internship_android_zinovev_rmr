package com.lockwood.core.extensions

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.addFragmentIfNotExist(
    containerLayoutId: Int,
    initFragment: () -> Fragment
) {
    val fm = supportFragmentManager
    val fragment = fm.findFragmentById(containerLayoutId)
    if (fragment == null) {
        fm.transact {
            add(containerLayoutId, initFragment())
        }
    }
}

inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    try {
        beginTransaction().apply {
            action()
        }.commitAllowingStateLoss()
    } catch (e: IllegalStateException) {
        Log.e("FragmentExt.transact:", e.toString())
    }
}