package com.salihaksit.mm.data

import com.salihaksit.mm.R
import com.salihaksit.mm.view.adapter.LayoutModel

data class CellViewEntity(
    var option: String
) : LayoutModel {
    override fun layoutId(): Int {
        return R.layout.item_cell
    }

}