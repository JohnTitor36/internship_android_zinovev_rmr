package com.lockwood.core.ui

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    protected fun setDisplayHomeAsUpEnabled(isEnabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isEnabled)
    }

    protected fun disableAppBarTitle() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}