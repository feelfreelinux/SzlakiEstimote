package io.feelfree.estimotezabytki.utils

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

fun RecyclerView.prepare() {
    setHasFixedSize(true) // For better performance
    layoutManager = LinearLayoutManager(context)
}