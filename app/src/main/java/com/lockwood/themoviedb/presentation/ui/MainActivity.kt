package com.lockwood.themoviedb.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.navigation.ui.setupWithNavController
import com.lockwood.core.extensions.findNavController
import com.lockwood.core.extensions.launchActivity
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.ui.BaseActivity
import com.lockwood.core.viewbinding.inflateViewBinding
import com.lockwood.themoviedb.R
import com.lockwood.themoviedb.databinding.ActivityMainBinding
import com.lockwood.themoviedb.di.component.DaggerMainComponent
import com.lockwood.themoviedb.login.presentation.ui.LoginActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var userPreferences: UserPreferences

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)

        binding = layoutInflater.inflateViewBinding()
        setContentView(binding.root)

        setBottomNavigation()
        checkIsUserLoggedIn()
    }

    private fun setBottomNavigation() {
        val navController = supportFragmentManager.findNavController(R.id.main_nav_host_fragment)
        binding.navigation.setupWithNavController(navController)
    }

    private fun checkIsUserLoggedIn() {
        val userLoggedIn = userPreferences.isUserLoggedIn()
        if (!userLoggedIn) {
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
