package com.example.superheroapp.extensions

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onLoadMore (action: ()-> Unit){
    addOnScrollListener(object:RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            (recyclerView.layoutManager as? GridLayoutManager)?.onLoadMore(action)
        }
    })
}