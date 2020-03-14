package com.lockwood.themoviedb.login.presentation.ui

import android.os.Bundle
import com.lockwood.core.extensions.color
import com.lockwood.core.ui.BaseActivity
import com.lockwood.themoviedb.login.R

class LoginActivity : BaseActivity(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.navigationBarColor = color(R.color.bg_black)
        super.onCreate(savedInstanceState)
    }

}