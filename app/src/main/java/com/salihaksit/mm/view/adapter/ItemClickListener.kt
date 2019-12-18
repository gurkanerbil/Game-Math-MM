package com.salihaksit.mm.view.adapter

import android.view.View

interface ItemClickListener<T> {

    fun onItemClick(view: View, item: T)

}