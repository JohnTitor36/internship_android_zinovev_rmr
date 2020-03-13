package com.lockwood.themoviedb.login.presentation

import com.lockwood.core.toaster.Toaster
import com.lockwood.core.ui.BaseViewModel

class LoginViewModel(toaster: Toaster) : BaseViewModel() {

    init {
        toaster.toast("LoginViewModel")
    }


}