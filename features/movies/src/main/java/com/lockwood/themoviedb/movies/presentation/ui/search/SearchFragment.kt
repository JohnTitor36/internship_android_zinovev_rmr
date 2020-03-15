package com.lockwood.themoviedb.movies.presentation.ui.search

import android.content.Context
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.di.component.search.DaggerSearchComponent
import javax.inject.Inject
import javax.inject.Provider

class SearchFragment : BaseFragment(R.layout.fragment_search) {

    @Inject
    lateinit var viewModelFactory: Provider<SearchViewModel>

    private lateinit var viewModel: SearchViewModel

    override val hasOptionMenu: Boolean = true

    override fun onAttach(context: Context) {
        inject()
        viewModel = viewModelFactory.get()
        super.onAttach(context)
    }

    private fun inject() {
        DaggerSearchComponent.builder()
            .build()
            .inject(this)
    }

}