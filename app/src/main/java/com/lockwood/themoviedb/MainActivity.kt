package com.lockwood.themoviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lockwood.themoviedb.extensions.launchActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchActivity<LoginActivity>()
    }
}
