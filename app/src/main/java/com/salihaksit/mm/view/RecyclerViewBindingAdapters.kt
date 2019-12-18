package com.salihaksit.mm.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("scheduleLayoutAnimation")
fun setScheduleLayoutAnimation(view: RecyclerView, animate: Boolean?) {
    if (animate == null || !animate)
        return

    view.scheduleLayoutAnimation()
}