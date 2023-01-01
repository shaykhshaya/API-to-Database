package com.shaya.curd.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shaya.curd.R

fun ImageView.loadImageByURL(context: Context,  url: String?){
    Glide
        .with(context)
        .load(url)
        .fitCenter()
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_connection_error)
        .into(this)

}