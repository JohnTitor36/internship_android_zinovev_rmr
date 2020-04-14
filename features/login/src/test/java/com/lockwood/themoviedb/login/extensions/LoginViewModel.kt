package com.lockwood.themoviedb.login.extensions

import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.test.schedulers.TestSchedulersProvider
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.lockwood.themoviedb.login.presentation.ui.LoginViewModel
import com.nhaarman.mockitokotlin2.mock

fun createLoginViewModel(
    authenticationRepository: AuthenticationRepository,
    schedulers: SchedulersProvider = TestSchedulersProvider(),
    userPreferences: UserPreferences = mock(),
    resourceReader: ResourceReader = mock()
): LoginViewModel {
    return LoginViewModel(
        authenticationRepository = authenticationRepository,
        userPreferences = userPreferences,
        resourceReader = resourceReader,
        schedulers = schedulers
    )
}