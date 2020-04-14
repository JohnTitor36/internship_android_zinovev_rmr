package com.lockwood.themoviedb.movies.presentation.ui.adapter

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.RequestBuilder
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.ui.BaseAdapter
import com.lockwood.core.ui.BaseViewHolder
import com.lockwood.core.viewbinding.inflateItemViewBinding
import com.lockwood.glide.extensions.load
import com.lockwood.themoviedb.movies.databinding.ItemMovieEmtpyBinding
import com.lockwood.themoviedb.movies.databinding.ItemMovieGridBinding
import com.lockwood.themoviedb.movies.databinding.ItemMovieListBinding
import com.lockwood.themoviedb.movies.domain.model.MovieItem
import com.lockwood.themoviedb.movies.extensions.loadMoviePosterRequest
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesItemViewType.ITEM_VIEW_TYPE_LIST
import com.lockwood.themoviedb.movies.utils.MovieUtils.buildOriginalTitle
import com.lockwood.themoviedb.movies.utils.MovieUtils.ratingToColor

class MoviesAdapter(
    data: List<MovieItem> = emptyList(),
    val itemViewType: Int = ITEM_VIEW_TYPE_LIST
) : BaseAdapter<MovieItem>(data) {

    interface MoviesAdapterListener {

        fun onMovieClick(item: MovieItem)

    }

    lateinit var listener: MoviesAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_CONTENT -> {
                return if (itemViewType == ITEM_VIEW_TYPE_LIST) {
                    val binding = parent.inflateItemViewBinding<ItemMovieListBinding>()
                    MoviesListViewHolder(binding)
                } else {
                    val binding = parent.inflateItemViewBinding<ItemMovieGridBinding>()
                    MoviesGridViewHolder(binding)
                }
            }
            else -> {
                val binding = parent.inflateItemViewBinding<ItemMovieEmtpyBinding>()
                EmptyMoviesViewHolder(binding)
            }
        }
    }

    abstract inner class MoviesViewHolder<T : ViewBinding>(private val itemBinding: T) :
        BaseViewHolder(itemBinding.root) {

        override fun onBind(position: Int) {
            val movie = data[position]

            val context = itemBinding.root.context
            val resourceReader = ResourceReader(context)
            val imageRequest = context.loadMoviePosterRequest()

            val ratingColor = ratingToColor(resourceReader, movie.voteAverage)

            bindMovie(itemBinding, movie, imageRequest, ratingColor)
        }

        abstract fun bindMovie(
            itemBinding: T,
            movie: MovieItem,
            avatarRequest: RequestBuilder<Drawable>,
            ratingColor: Int
        )
    }

    inner class MoviesListViewHolder(itemBinding: ItemMovieListBinding) :
        MoviesViewHolder<ItemMovieListBinding>(itemBinding) {

        override fun bindMovie(
            itemBinding: ItemMovieListBinding,
            movie: MovieItem,
            avatarRequest: RequestBuilder<Drawable>,
            ratingColor: Int
        ) = with(itemBinding) {
            val resourceReader = ResourceReader(itemBinding.root.context)

            itemMovieTitle.text = movie.title
            itemMovieOriginalTitle.text = buildOriginalTitle(
                resourceReader = resourceReader,
                title = movie.originalTitle,
                releaseDate = movie.releaseDate
            )

            itemMovieVoteAverage.setTextColor(ratingColor)
            itemMovieVoteAverage.text = movie.voteAverage.toString()
            itemMoviePopularity.text = movie.popularity.toString()

            itemMovieImage.load(
                url = movie.poster,
                request = avatarRequest
            ).waitForLayout()

            root.setOnClickListener { listener.onMovieClick(movie) }
        }
    }

    inner class MoviesGridViewHolder(itemBinding: ItemMovieGridBinding) :
        MoviesViewHolder<ItemMovieGridBinding>(itemBinding) {

        override fun bindMovie(
            itemBinding: ItemMovieGridBinding,
            movie: MovieItem,
            avatarRequest: RequestBuilder<Drawable>,
            ratingColor: Int
        ) = with(itemBinding) {
            val resourceReader = ResourceReader(itemBinding.root.context)

            itemMovieTitle.text = movie.title
            itemMovieOriginalTitle.text = buildOriginalTitle(
                resourceReader = resourceReader,
                title = movie.originalTitle,
                releaseDate = movie.releaseDate
            )

            itemMovieVoteAverage.setTextColor(ratingColor)
            itemMovieVoteAverage.text = movie.voteAverage.toString()
            itemMoviePopularity.text = movie.popularity.toString()

            itemMovieImage.load(
                url = movie.poster,
                request = avatarRequest
            ).waitForLayout()

            root.setOnClickListener { listener.onMovieClick(movie) }
        }
    }

    inner class EmptyMoviesViewHolder(itemBinding: ItemMovieEmtpyBinding) :
        BaseViewHolder(itemBinding.root) {

        override fun onBind(position: Int) = Unit
    }

}