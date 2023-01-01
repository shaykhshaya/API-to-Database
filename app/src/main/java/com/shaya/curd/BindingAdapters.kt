package com.shaya.curd

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shaya.curd.network.ProductItem
import com.shaya.curd.ui.AddProductGridAdapter
import com.shaya.curd.ui.viewmodel.ProductApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
        {

            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)

        }
    }
}


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
data: List<ProductItem>?){
    val adapter = recyclerView.adapter as AddProductGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("productApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: ProductApiStatus
){

    when(status){
        ProductApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ProductApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ProductApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

