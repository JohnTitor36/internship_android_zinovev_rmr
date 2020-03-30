package com.lockwood.core.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity : AppCompatActivity() {

    fun setContentView(viewBinding: ViewBinding) {
        val view = viewBinding.root
        setContentView(view)
    }

    protected fun setDisplayHomeAsUpEnabled(isEnabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isEnabled)
    }

    protected fun disableAppBarTitle() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}