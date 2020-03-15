package com.lockwood.themoviedb.movies.presentation.ui.list

import android.content.Context
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.di.component.list.DaggerMoviesListComponent
import javax.inject.Inject
import javax.inject.Provider

class MoviesListFragment : BaseFragment(R.layout.fragment_movies_list) {

    @Inject
    lateinit var viewModelFactory: Provider<MoviesListViewModel>

    private lateinit var viewModel: MoviesListViewModel

    override val hasOptionMenu: Boolean = true

    override fun onAttach(context: Context) {
        inject()
        viewModel = viewModelFactory.get()
        super.onAttach(context)
    }

    private fun inject() {
        DaggerMoviesListComponent.builder()
            .build()
            .inject(this)
    }

}