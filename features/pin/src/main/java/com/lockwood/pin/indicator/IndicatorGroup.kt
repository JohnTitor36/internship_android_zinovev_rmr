package com.lockwood.pin.indicator

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lockwood.pin.R
import com.lockwood.pin.indicator.adapter.IndicatorAdapter
import com.lockwood.pin.keyboard.PinKeyboard
import com.lockwood.pin.keyboard.listener.PinKeyboardListener
import kotlinx.android.synthetic.main.keyboard_pin.view.*

@ExperimentalStdlibApi
class IndicatorGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val indicatorAdapter = IndicatorAdapter()

    private val pinKeyboardListener = object : PinKeyboardListener {

        override fun onDigitClick(digit: Int) {
            indicatorAdapter.listener.onDigitEntered()
        }

        override fun onClearDigitClick() {
            indicatorAdapter.listener.onDigitCleared()
        }

        override fun onResetEnteredDigits() {
            indicatorAdapter.listener.onAllCleared()
        }

        override fun onLastItemEntered(digits: String) = Unit

        override fun onExitClick() = Unit

    }

    init {
        inflateView()
        setupLayoutManger()
    }

    fun setupWithPinKeyboard(pinKeyboard: PinKeyboard) {
        pinKeyboard.addPinKeyboardListener(pinKeyboardListener)
    }

    fun showError() {
        indicatorAdapter.listener.onShowError()
    }

    private fun inflateView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.indiactor_group, this)
    }

    private fun setupLayoutManger() {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = indicatorAdapter
        }
    }

}