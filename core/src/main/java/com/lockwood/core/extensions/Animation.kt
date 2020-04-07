package com.lockwood.core.extensions

import android.view.View
import android.view.ViewGroup
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager

fun View.beginDelayedTransition() {
    TransitionManager.beginDelayedTransition(
        rootView as ViewGroup,
        AutoTransition()
    )
}