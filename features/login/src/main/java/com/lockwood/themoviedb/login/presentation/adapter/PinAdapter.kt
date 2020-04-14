package com.lockwood.themoviedb.login.presentation.adapter

import android.view.ViewGroup
import com.lockwood.core.ui.BaseAdapter
import com.lockwood.core.ui.BaseViewHolder
import com.lockwood.core.viewbinding.inflateItemViewBinding
import com.lockwood.themoviedb.login.databinding.ItemPinDigitBinding
import com.lockwood.themoviedb.login.databinding.ItemPinEmptyBinding
import com.lockwood.themoviedb.login.presentation.adapter.PinItemViewType.ITEM_VIEW_TYPE_DIGIT

class PinAdapter(
    data: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0),
    val itemViewType: Int = ITEM_VIEW_TYPE_DIGIT
) : BaseAdapter<Int>(data) {

    interface PinAdapterListener {

        fun onDigitClick(digit: Int)

        fun onClearClick()

        fun onExitClick()

    }

    lateinit var listener: PinAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_CONTENT -> {
                return when (itemViewType) {
//                    ITEM_VIEW_TYPE_CLEAR -> {
//                        val binding = parent.inflateItemViewBinding<ItemPinIconBinding>()
//                        MoviesListViewHolder(binding)
//                    }
//                    ITEM_VIEW_TYPE_EXIT -> {
//                        val binding = parent.inflateItemViewBinding<ItemPinTextBinding>()
//                        MoviesGridViewHolder(binding)
//                    }
                    else -> {
                        val binding = parent.inflateItemViewBinding<ItemPinDigitBinding>()
                        PinDigitViewHolder(binding)
                    }
                }
            }
            else -> {
                val binding = parent.inflateItemViewBinding<ItemPinEmptyBinding>()
                EmptyMoviesViewHolder(binding)
            }
        }
    }

    inner class PinDigitViewHolder(private val itemBinding: ItemPinDigitBinding) :
        BaseViewHolder(itemBinding.root) {

        override fun onBind(position: Int) {
            val digit = data[position]
            itemBinding.itemPinDigitButton.text = digit.toString()
        }

    }

    inner class EmptyMoviesViewHolder(itemBinding: ItemPinEmptyBinding) :
        BaseViewHolder(itemBinding.root) {

        override fun onBind(position: Int) = Unit
    }

}