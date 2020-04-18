package com.lockwood.pin.keyboard.adapter.base

import androidx.recyclerview.widget.RecyclerView

internal abstract class BaseAdapter<T>(
    protected val data: List<T>,
    protected val enteredDigits: MutableList<Int>
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBind(data[position])
    }

}