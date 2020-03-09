package com.lockwood.core.ui

import android.os.Bundle

abstract class BaseFragmentActivity : BaseActivity() {

    abstract val layoutId: Int

    abstract val containerId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

}