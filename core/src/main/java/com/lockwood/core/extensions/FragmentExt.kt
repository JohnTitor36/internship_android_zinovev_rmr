package com.lockwood.core.extensions

import androidx.fragment.app.Fragment
import com.lockwood.core.di.provider.ApplicationProvider

val Fragment.applicationProvider: ApplicationProvider
    get() = requireActivity().applicationProvider