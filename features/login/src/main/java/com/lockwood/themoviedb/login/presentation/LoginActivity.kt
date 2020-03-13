package com.lockwood.themoviedb.login.presentation

import android.os.Bundle
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.toaster.Toaster
import com.lockwood.core.ui.BaseActivity
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.component.DaggerLoginComponent
import javax.inject.Inject

class LoginActivity : BaseActivity(R.layout.activity_login) {

    @Inject
    lateinit var toaster: Toaster

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    private fun inject() {
        DaggerLoginComponent.builder()
            .applicationProvider(appToolsProvider)
            .build()
            .inject(this)
    }

}