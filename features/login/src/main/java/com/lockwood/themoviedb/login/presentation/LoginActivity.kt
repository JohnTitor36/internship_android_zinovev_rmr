package com.lockwood.themoviedb.login.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.lockwood.core.ui.BaseActivity
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.component.DaggerLoginComponent
import com.lockwood.themoviedb.login.di.module.LoginActivityModule
import javax.inject.Inject

class LoginActivity : BaseActivity(R.layout.activity_login) {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
    }

    private fun inject() {
        DaggerLoginComponent.builder()
            .activity(this)
            .loginActivityModule(LoginActivityModule(this))
            .build()
            .inject(this)
    }

}