package com.example.superheroapp.extensions

import androidx.recyclerview.widget.GridLayoutManager

fun GridLayoutManager.onLoadMore(action: ()-> Unit){
    val lastVisiblePosition = findLastVisibleItemPosition()
    if (lastVisiblePosition == itemCount - 3){
        action.invoke()
    }
}