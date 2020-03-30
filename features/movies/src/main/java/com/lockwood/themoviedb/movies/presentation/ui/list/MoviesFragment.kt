package com.lockwood.themoviedb.movies.presentation.ui.list

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.di.component.list.DaggerMoviesComponent
import javax.inject.Inject

class MoviesFragment : BaseFragment(R.layout.fragment_movies) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }

    override val hasOptionMenu: Boolean = true

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    private fun inject() {
        DaggerMoviesComponent.builder()
            .build()
            .inject(this)
    }

}