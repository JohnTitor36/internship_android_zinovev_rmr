package com.lockwood.themoviedb.ui

import android.content.Intent
import android.os.Bundle
import com.lockwood.core.extensions.launchActivity
import com.lockwood.core.ui.BaseActivity
import com.lockwood.themoviedb.R
import com.lockwood.themoviedb.di.component.DaggerMainComponent
import com.lockwood.themoviedb.login.presentation.LoginActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)

        launchActivity<LoginActivity> {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

    private fun inject() {
        DaggerMainComponent.builder()
            .build()
            .inject(this)
    }

}
