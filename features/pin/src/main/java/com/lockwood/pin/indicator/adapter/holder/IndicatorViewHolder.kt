package com.lockwood.pin.indicator.adapter.holder

import android.content.res.ColorStateList
import com.lockwood.core.reader.ResourceReader
import com.lockwood.pin.R
import com.lockwood.pin.databinding.ItemIndiactorBinding
import com.lockwood.pin.indicator.adapter.base.BaseViewHolder

internal class IndicatorViewHolder(
     itemBinding: ItemIndiactorBinding
) : BaseViewHolder(itemBinding) {

     private val resourceReader
          get() = ResourceReader(itemBinding.root.context)

     private val colorEnabled = resourceReader.color(R.color.indicator_color_enabled)
     private val colorDisabled = resourceReader.color(R.color.indicator_color_disabled)
     private val errorColor = resourceReader.color(R.color.indicator_color_error)

     private val viewStates = arrayOf(
          intArrayOf(android.R.attr.state_enabled),
          intArrayOf(-android.R.attr.state_enabled)
     )

     private val defaultColors = intArrayOf(
          colorEnabled,
          colorDisabled
     )

     override fun onBind(position: Int, selectedItemsCount: Int, isShowError: Boolean) {

          with((itemBinding as ItemIndiactorBinding).indicatorImage) {
               if (!isShowError) {
                    isEnabled = position < selectedItemsCount
                    imageTintList = ColorStateList(viewStates, defaultColors)
               } else {
                    imageTintList = ColorStateList.valueOf(errorColor)
               }
          }
     }

}