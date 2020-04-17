package com.lockwood.pin

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.lockwood.pin.adapter.PinAdapter
import com.lockwood.pin.adapter.listener.PinKeyboardListener
import kotlinx.android.synthetic.main.keyboard_pin.view.*

@ExperimentalStdlibApi
class PinKeyboard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {

        const val SPAN_COUNT = 3

    }

    private val pinAdapter = PinAdapter()

    var pinKeyboardListener: PinKeyboardListener? = null
        set(value) {
            field = value
            pinAdapter.listener = requireNotNull(value)
        }

    init {
        inflateView()
        setupLayoutManger()
    }

    private fun inflateView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.keyboard_pin, this)
    }

    private fun setupLayoutManger() {
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            adapter = pinAdapter
        }
    }

    fun resetEnteredDigits() {
        pinAdapter.resetEnteredDigits()
    }

}