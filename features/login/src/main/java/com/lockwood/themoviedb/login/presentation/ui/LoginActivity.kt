package com.lockwood.themoviedb.login.presentation.ui

import android.os.Bundle
import com.lockwood.core.ui.BaseActivity
import com.lockwood.core.viewbinding.inflateViewBinding
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Edge)
        super.onCreate(savedInstanceState)
        disableAbilityToScreenshot()

        binding = layoutInflater.inflateViewBinding()
        setContentView(binding.root)
    }

}