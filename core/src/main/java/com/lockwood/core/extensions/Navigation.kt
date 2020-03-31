package com.lockwood.core.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

fun FragmentManager.findNavController(@IdRes hostFragmentId: Int): NavController {
    val hostFragment = findFragmentById(hostFragmentId)
    return (hostFragment as NavHostFragment).findNavController()
}