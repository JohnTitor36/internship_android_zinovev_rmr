package com.lockwood.themoviedb.login.presentation

import android.os.Bundle
import com.lockwood.core.extensions.addFragmentIfNotExist
import com.lockwood.core.ui.BaseFragmentActivity
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.inject

class LoginActivity : BaseFragmentActivity() {

    override val layoutId = R.layout.activity_login

    override val containerId = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()

        if (savedInstanceState == null) {
            addFragmentIfNotExist(containerId) { LoginFragment.newInstance() }
        }
    }

}