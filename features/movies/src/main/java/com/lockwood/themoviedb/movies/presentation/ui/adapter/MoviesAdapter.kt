package com.lockwood.themoviedb.movies.presentation.ui.adapter

import android.view.ViewGroup
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.ui.BaseAdapter
import com.lockwood.core.ui.BaseViewHolder
import com.lockwood.core.viewbinding.inflateViewBinding
import com.lockwood.glide.extensions.defaultPlaceholder
import com.lockwood.glide.extensions.drawableRequest
import com.lockwood.glide.extensions.load
import com.lockwood.themoviedb.movies.databinding.ItemMovieEmtpyBinding
import com.lockwood.themoviedb.movies.databinding.ItemMovieListBinding
import com.lockwood.themoviedb.movies.domain.model.Movie

class MoviesAdapter(data: MutableList<Movie> = mutableListOf()) :
    BaseAdapter<Movie>(data) {

    interface MoviesAdapterListener {

        fun onMovieClick(item: Movie)

    }

    lateinit var listener: MoviesAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val itemMoviesBinding =
                    parent.inflateViewBinding<ItemMovieListBinding>(attachToRoot = false)
                MoviesViewHolder(itemMoviesBinding)
            }
            else -> {
                val itemMoviesBinding =
                    parent.inflateViewBinding<ItemMovieEmtpyBinding>(attachToRoot = false)
                EmptyMoviesViewHolder(itemMoviesBinding)
            }
        }
    }

    inner class MoviesViewHolder(private val itemBinding: ItemMovieListBinding) :
        BaseViewHolder(itemBinding.root) {

        override fun onBind(position: Int) = with(itemBinding) {
            val movie = data[position]

            val context = root.context
            val resourceReader = ResourceReader(context)
            val placeholder = resourceReader.defaultPlaceholder
            val imageUlr = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/${movie.posterPath}"
            val avatarRequest = context.drawableRequest(
                resourceReader = resourceReader,
                placeholder = placeholder,
                fallback = placeholder,
                error = placeholder
            )
            itemMovieImage.load(imageUlr, avatarRequest).waitForLayout()

            itemMovieTitle.text = movie.title
            itemMovieEngTitle.text = movie.originalTitle
            itemMovieVoteAverage.text = movie.voteAverage.toString()
            itemMoviePopularity.text = movie.popularity.toString()

            root.setOnClickListener { listener.onMovieClick(movie) }
        }
    }

    inner class EmptyMoviesViewHolder(private val itemBinding: ItemMovieEmtpyBinding) :
        BaseViewHolder(itemBinding.root) {

        override fun onBind(position: Int) = Unit
    }


}