package com.shaya.curd.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.curd.databinding.AddItemListBinding
import com.shaya.curd.network.ProductItem

class AddProductGridAdapter: ListAdapter<ProductItem,
        AddProductGridAdapter.AddProductViewHolder>(DiffCallback) {

            class AddProductViewHolder(private var binding: AddItemListBinding):RecyclerView.ViewHolder(binding.root){

                fun bind(productItem: ProductItem){
                    binding.photo = productItem
                    binding.executePendingBindings()

                }

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProductViewHolder {
        val adapterLayout = AddItemListBinding.inflate(LayoutInflater.from(parent.context))
        return AddProductViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AddProductViewHolder, position: Int) {
        val productPhoto = getItem(position)
        holder.bind(productPhoto)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ProductItem>()
    {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.image == newItem.image
        }

    }


}