package com.lockwood.themoviedb.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lockwood.core.extensions.launchActivity
import com.lockwood.themoviedb.R
import com.lockwood.themoviedb.di.inject
import com.lockwood.themoviedb.login.presentation.LoginActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()

        launchActivity<LoginActivity> {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

}
