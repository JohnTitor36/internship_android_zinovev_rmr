package com.lockwood.themoviedb.movies.presentation.ui

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.di.component.DaggerMovieComponent
import javax.inject.Inject

class MovieFragment : BaseFragment(R.layout.fragment_movie) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }

    override val hasOptionMenu: Boolean = true

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    private fun inject() {
        DaggerMovieComponent.builder()
            .build()
            .inject(this)
    }

}