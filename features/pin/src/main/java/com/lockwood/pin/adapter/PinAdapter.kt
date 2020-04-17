package com.lockwood.pin.adapter

import android.view.ViewGroup
import com.lockwood.core.viewbinding.inflateItemViewBinding
import com.lockwood.pin.PinKeyboard.Companion.SPAN_COUNT
import com.lockwood.pin.adapter.PinItemViewType.ITEM_VIEW_TYPE_CLEAR
import com.lockwood.pin.adapter.PinItemViewType.ITEM_VIEW_TYPE_DIGIT
import com.lockwood.pin.adapter.PinItemViewType.ITEM_VIEW_TYPE_EXIT
import com.lockwood.pin.adapter.base.BaseAdapter
import com.lockwood.pin.adapter.base.BaseViewHolder
import com.lockwood.pin.adapter.holder.PinClearViewHolder
import com.lockwood.pin.adapter.holder.PinDigitViewHolder
import com.lockwood.pin.adapter.holder.PinEmptyViewHolder
import com.lockwood.pin.adapter.holder.PinExitViewHolder
import com.lockwood.pin.adapter.listener.PinKeyboardListener
import com.lockwood.pin.databinding.ItemPinClearBinding
import com.lockwood.pin.databinding.ItemPinDigitBinding
import com.lockwood.pin.databinding.ItemPinEmptyBinding
import com.lockwood.pin.databinding.ItemPinExitBinding

@ExperimentalStdlibApi
internal class PinAdapter constructor(
    data: List<Int> = ITEM_POSITIONS_ARRAY,
    enteredDigits: MutableList<Int> = mutableListOf()
) : BaseAdapter<Int>(data, enteredDigits) {

    companion object {

        private const val ITEM_POSITION_CLEAR = 9
        private const val ITEM_POSITION_EXIT = 11

        private val ITEM_POSITIONS_ARRAY = buildList {
            addAll(List(ITEM_POSITION_CLEAR) { i -> i + 1 }) // 1..9
            addAll(List(SPAN_COUNT) { _ -> 0 }) // 0, 0, 0
        }

    }

    lateinit var listener: PinKeyboardListener

    override fun getItemViewType(position: Int): Int {
        return if (!data.isNullOrEmpty()) {
            when (position) {
                ITEM_POSITION_CLEAR -> ITEM_VIEW_TYPE_CLEAR
                ITEM_POSITION_EXIT -> ITEM_VIEW_TYPE_EXIT
                else -> ITEM_VIEW_TYPE_DIGIT
            }
        } else {
            VIEW_TYPE_EMPTY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Int> {
        return when (viewType) {
            ITEM_VIEW_TYPE_DIGIT -> {
                val binding = parent.inflateItemViewBinding<ItemPinDigitBinding>()
                PinDigitViewHolder(binding, listener, enteredDigits)
            }
            ITEM_VIEW_TYPE_CLEAR -> {
                val binding = parent.inflateItemViewBinding<ItemPinClearBinding>()
                PinClearViewHolder(binding, listener, enteredDigits)
            }
            ITEM_VIEW_TYPE_EXIT -> {
                val binding = parent.inflateItemViewBinding<ItemPinExitBinding>()
                PinExitViewHolder(binding, listener)
            }
            else -> {
                val binding = parent.inflateItemViewBinding<ItemPinEmptyBinding>()
                PinEmptyViewHolder(binding, listener)
            }
        }
    }

    fun resetEnteredDigits() {
        enteredDigits.clear()
    }

}