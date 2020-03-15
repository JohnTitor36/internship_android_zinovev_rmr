package com.lockwood.themoviedb.presentation.ui

import android.os.Bundle
import com.lockwood.core.ui.BaseActivity
import com.lockwood.themoviedb.R
import com.lockwood.themoviedb.di.component.DaggerMainComponent
import kotlinx.android.synthetic.main.include_app_bar.*
import kotlinx.android.synthetic.main.include_app_bar.view.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)

        setSupportActionBar(appbar.toolbar)
        disableAppBarTitle()
        setDisplayHomeAsUpEnabled(false)

//        launchActivity<LoginActivity> {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
    }



    private fun inject() {
        DaggerMainComponent.builder()
            .build()
            .inject(this)
    }

}
