package com.lockwood.core.ui

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected fun setDisplayHomeAsUpEnabled(isEnabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isEnabled)
    }

    protected fun disableAppBarTitle() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}