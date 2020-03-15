package com.lockwood.themoviedb.movies.presentation.ui

import android.content.Context
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.di.component.DaggerMovieComponent
import javax.inject.Inject
import javax.inject.Provider

class MovieFragment : BaseFragment(R.layout.fragment_movie) {

    @Inject
    lateinit var viewModelFactory: Provider<MovieViewModel>

    private lateinit var viewModel: MovieViewModel

    override val hasOptionMenu: Boolean = true

    override fun onAttach(context: Context) {
        inject()
        viewModel = viewModelFactory.get()
        super.onAttach(context)
    }

    private fun inject() {
        DaggerMovieComponent.builder()
            .build()
            .inject(this)
    }

}