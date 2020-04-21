package com.lockwood.core.event

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

class NavigationDirectionEvent(val direction: NavDirections, val navOptions: NavOptions? = null) : Event