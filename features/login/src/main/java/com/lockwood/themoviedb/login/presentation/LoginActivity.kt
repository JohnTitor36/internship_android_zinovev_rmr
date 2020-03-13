package com.lockwood.themoviedb.login.presentation

import android.os.Bundle
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.ui.BaseActivity
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.component.DaggerLoginComponent

class LoginActivity : BaseActivity(R.layout.activity_login) {

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