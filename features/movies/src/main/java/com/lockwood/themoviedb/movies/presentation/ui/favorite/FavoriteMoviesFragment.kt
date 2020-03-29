package com.lockwood.themoviedb.movies.presentation.ui.favorite

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.di.component.favorite.DaggerFavoriteMoviesComponent
import javax.inject.Inject

class FavoriteMoviesFragment : BaseFragment(R.layout.fragment_favorite_movies) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FavoriteMoviesViewModel by viewModels { viewModelFactory }

    override val hasOptionMenu: Boolean = true

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    private fun inject() {
        DaggerFavoriteMoviesComponent.builder()
            .build()
            .inject(this)
    }

}