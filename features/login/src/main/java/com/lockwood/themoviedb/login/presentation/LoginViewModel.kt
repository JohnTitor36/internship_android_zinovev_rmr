package com.lockwood.themoviedb.login.presentation

import com.lockwood.core.toaster.Toaster
import com.lockwood.core.ui.BaseViewModel
import com.lockwood.themoviedb.login.domain.CredentialsValidator
import javax.inject.Inject

class LoginViewModel @Inject
constructor(
    private val toaster: Toaster,
    private val credentialsValidator: CredentialsValidator
) : BaseViewModel() {

}