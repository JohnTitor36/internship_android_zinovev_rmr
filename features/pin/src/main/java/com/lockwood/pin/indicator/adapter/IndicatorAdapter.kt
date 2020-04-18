package com.lockwood.pin.indicator.adapter

import android.view.ViewGroup
import com.lockwood.core.viewbinding.inflateItemViewBinding
import com.lockwood.pin.databinding.ItemIndiactorBinding
import com.lockwood.pin.indicator.adapter.base.BaseAdapter
import com.lockwood.pin.indicator.adapter.holder.IndicatorViewHolder
import com.lockwood.pin.indicator.listener.IndicatorGroupListener

@ExperimentalStdlibApi
internal class IndicatorAdapter(
    override var selectedItemsCount: Int = DEFAULT_SELECTED_ITEMS_COUNT,
    override var isShowError: Boolean = DEFAULT_IS_SHOW_ERROR
) : BaseAdapter(selectedItemsCount, isShowError) {

    companion object {

        private const val PIN_MAX_COUNT = 4
        private const val DEFAULT_SELECTED_ITEMS_COUNT = 0
        private const val DEFAULT_IS_SHOW_ERROR = false
    }

    val listener = object : IndicatorGroupListener {

        override fun onDigitCleared() {
            selectedItemsCount = safeDecrement()
            this@IndicatorAdapter.notifyDataSetChanged()
        }

        override fun onDigitEntered() {
            selectedItemsCount = safeIncrement()
            this@IndicatorAdapter.notifyDataSetChanged()
        }

        override fun onAllCleared() {
            selectedItemsCount = DEFAULT_SELECTED_ITEMS_COUNT
            isShowError = DEFAULT_IS_SHOW_ERROR
            this@IndicatorAdapter.notifyDataSetChanged()
        }

        override fun onShowError() {
            isShowError = true
            this@IndicatorAdapter.notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val binding = parent.inflateItemViewBinding<ItemIndiactorBinding>()
        return IndicatorViewHolder(binding)
    }

    private fun safeDecrement(): Int {
        with(selectedItemsCount) {
            return if (this > 0) {
                this - 1
            } else {
                this
            }
        }
    }

    private fun safeIncrement(): Int {
        with(selectedItemsCount) {
            return if (this < PIN_MAX_COUNT) {
                this + 1
            } else {
                this
            }
        }
    }

}