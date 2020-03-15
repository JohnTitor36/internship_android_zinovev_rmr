package com.lockwood.themoviedb.movies.presentation.ui.favorite

import android.content.Context
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.di.component.favorite.DaggerFavoriteMoviesComponent
import javax.inject.Inject
import javax.inject.Provider

class FavoriteMoviesFragment : BaseFragment(R.layout.fragment_favorite_movies) {

    @Inject
    lateinit var viewModelFactory: Provider<FavoriteMoviesViewModel>

    private lateinit var viewModel: FavoriteMoviesViewModel

    override val hasOptionMenu: Boolean = true

    override fun onAttach(context: Context) {
        inject()
        viewModel = viewModelFactory.get()
        super.onAttach(context)
    }

    private fun inject() {
        DaggerFavoriteMoviesComponent.builder()
            .build()
            .inject(this)
    }

}