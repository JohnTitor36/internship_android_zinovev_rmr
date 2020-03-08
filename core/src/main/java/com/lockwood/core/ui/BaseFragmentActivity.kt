package com.lockwood.core.ui

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragmentActivity : BaseActivity() {

    abstract val layoutId: Int

    abstract val containerId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        if (savedInstanceState == null) {
            showFragment()
        }

    }

    abstract fun showFragment()

    @Suppress("UNCHECKED_CAST")
    inline fun <T : Fragment> checkFragmentOrInit(
        init: () -> T
    ) {
        var fragment: T? = supportFragmentManager.findFragmentById(containerId) as T?

        if (fragment == null) {
            fragment = init()
            supportFragmentManager.beginTransaction()
                .add(containerId, fragment)
                .commit()
        }

    }
}