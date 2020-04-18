package com.lockwood.pin.keyboard.adapter

import android.view.ViewGroup
import com.lockwood.core.viewbinding.inflateItemViewBinding
import com.lockwood.pin.databinding.ItemPinClearBinding
import com.lockwood.pin.databinding.ItemPinDigitBinding
import com.lockwood.pin.databinding.ItemPinEmptyBinding
import com.lockwood.pin.databinding.ItemPinExitBinding
import com.lockwood.pin.keyboard.PinKeyboard.Companion.SPAN_COUNT
import com.lockwood.pin.keyboard.adapter.PinItemViewType.ITEM_VIEW_TYPE_CLEAR
import com.lockwood.pin.keyboard.adapter.PinItemViewType.ITEM_VIEW_TYPE_DIGIT
import com.lockwood.pin.keyboard.adapter.PinItemViewType.ITEM_VIEW_TYPE_EXIT
import com.lockwood.pin.keyboard.adapter.base.BaseAdapter
import com.lockwood.pin.keyboard.adapter.base.BaseViewHolder
import com.lockwood.pin.keyboard.adapter.holder.PinClearViewHolder
import com.lockwood.pin.keyboard.adapter.holder.PinDigitViewHolder
import com.lockwood.pin.keyboard.adapter.holder.PinEmptyViewHolder
import com.lockwood.pin.keyboard.adapter.holder.PinExitViewHolder
import com.lockwood.pin.keyboard.listener.EnteredDigitsListener
import com.lockwood.pin.keyboard.listener.PinKeyboardListener

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

    private val enteredDigitsListener = EnteredDigitsListener(enteredDigits)

    private val listeners: MutableList<PinKeyboardListener> = mutableListOf(enteredDigitsListener)

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            ITEM_POSITION_CLEAR -> ITEM_VIEW_TYPE_CLEAR
            ITEM_POSITION_EXIT -> ITEM_VIEW_TYPE_EXIT
            else -> ITEM_VIEW_TYPE_DIGIT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Int> {
        return when (viewType) {
            ITEM_VIEW_TYPE_DIGIT -> {
                val binding = parent.inflateItemViewBinding<ItemPinDigitBinding>()
                PinDigitViewHolder(binding, listeners, enteredDigits)
            }
            ITEM_VIEW_TYPE_CLEAR -> {
                val binding = parent.inflateItemViewBinding<ItemPinClearBinding>()
                PinClearViewHolder(binding, listeners)
            }
            ITEM_VIEW_TYPE_EXIT -> {
                val binding = parent.inflateItemViewBinding<ItemPinExitBinding>()
                PinExitViewHolder(binding, listeners)
            }
            else -> {
                val binding = parent.inflateItemViewBinding<ItemPinEmptyBinding>()
                PinEmptyViewHolder(binding, listeners)
            }
        }
    }

    fun addPinKeyboardListener(pinKeyboardListener: PinKeyboardListener) {
        listeners.add(pinKeyboardListener)
    }

    fun resetEnteredDigits() {
        enteredDigits.clear()
    }

}