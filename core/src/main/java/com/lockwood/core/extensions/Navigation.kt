package com.lockwood.core.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

private const val DEFAULT_ANIMATIONS_VALUE = -1

fun NavOptions.buildNavOptions(): NavOptions {
    with(this) {
//            val enterAnimation = enterAnim.takeIf { it != DEFAULT_ANIMATIONS_VALUE }
//                ?: R.anim.default_enter_anim
//            val exitAnimation = exitAnim.takeIf { it != DEFAULT_ANIMATIONS_VALUE }
//                ?: R.anim.default_exit_anim
//            val popEnterAnimation = popEnterAnim.takeIf { it != DEFAULT_ANIMATIONS_VALUE }
//                ?: R.anim.default_pop_enter_anim
//            val popExitAnimation = popExitAnim.takeIf { it != DEFAULT_ANIMATIONS_VALUE }
//                ?: R.anim.default_pop_exit_anim

        return NavOptions.Builder()
//                .setEnterAnim(enterAnimation)
//                .setExitAnim(exitAnimation)
//                .setPopEnterAnim(popEnterAnimation)
//                .setPopExitAnim(popExitAnimation)
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