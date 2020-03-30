package com.lockwood.themoviedb.login.presentation.ui

import android.os.Bundle
import com.lockwood.core.extensions.color
import com.lockwood.core.ui.BaseActivity
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.navigationBarColor = color(R.color.bg_black)
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding)
    }

}