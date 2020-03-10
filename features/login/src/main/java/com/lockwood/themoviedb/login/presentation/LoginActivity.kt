package com.lockwood.themoviedb.login.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.lockwood.core.ui.BaseActivity
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.inject
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

}