package com.lockwood.themoviedb.presentation.ui

import android.content.Intent
import android.os.Bundle
import com.lockwood.core.extensions.launchActivity
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.ui.BaseActivity
import com.lockwood.themoviedb.R
import com.lockwood.themoviedb.di.component.DaggerMainComponent
import com.lockwood.themoviedb.login.presentation.ui.LoginActivity
import kotlinx.android.synthetic.main.include_app_bar.*
import kotlinx.android.synthetic.main.include_app_bar.view.*
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)

        setSupportActionBar(appbar.toolbar)
        disableAppBarTitle()
        setDisplayHomeAsUpEnabled(false)

        if (!userPreferences.fetchUserLoggedIn()) {
            launchActivity<LoginActivity> {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }

    private fun inject() {
        DaggerMainComponent.builder()
            .preferencesToolsProvider(preferencesToolsProvider)
            .build()
            .inject(this)
    }

}
