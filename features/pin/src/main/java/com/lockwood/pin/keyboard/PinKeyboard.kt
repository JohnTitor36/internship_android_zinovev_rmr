package com.lockwood.pin.keyboard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.lockwood.pin.R
import com.lockwood.pin.keyboard.adapter.PinAdapter
import com.lockwood.pin.keyboard.listener.PinKeyboardListener
import kotlinx.android.synthetic.main.keyboard_pin.view.*

@ExperimentalStdlibApi
class PinKeyboard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {

        private const val SPAN_COUNT = 3

    }

    private val pinAdapter = PinAdapter()

    init {
        inflateView()
        setupLayoutManger()
    }

    fun addPinKeyboardListener(pinKeyboardListener: PinKeyboardListener) {
        pinAdapter.addPinKeyboardListener(pinKeyboardListener)
    }

    private fun inflateView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.keyboard_pin, this)
    }

    private fun setupLayoutManger() {
        with(recyclerView) {
            layoutManager = GridLayoutManager(
                context,
                SPAN_COUNT
            )
            adapter = pinAdapter
        }
    }

}