package com.lockwood.core.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

fun NavOptions.buildNavOptions(): NavOptions {
    with(this) {
        return NavOptions.Builder()
            .setPopUpTo(popUpTo, isPopUpToInclusive)
            .setLaunchSingleTop(shouldLaunchSingleTop())
            .build()
    }
}

fun NavController.navOptionsFromAction(direction: NavDirections): NavOptions? {
    return currentDestination
        ?.getAction(direction.actionId)
        ?.navOptions

}

fun FragmentManager.findNavController(@IdRes hostFragmentId: Int): NavController {
    val hostFragment = findFragmentById(hostFragmentId)
    return (hostFragment as NavHostFragment).findNavController()
}