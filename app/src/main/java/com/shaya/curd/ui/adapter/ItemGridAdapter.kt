package com.shaya.curd.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.curd.databinding.GridItemBinding
import com.shaya.curd.network.ProductItem

class AddProductGridAdapter(val callback: (ProductItem) -> Unit): ListAdapter<ProductItem,
        AddProductGridAdapter.AddProductViewHolder>(DiffCallback) {

            class AddProductViewHolder(private var binding: GridItemBinding):RecyclerView.ViewHolder(binding.root){

                fun bind(productItem: ProductItem){
                    binding.photo = productItem
                    binding.executePendingBindings()

                }

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProductViewHolder {
        val adapterLayout = GridItemBinding.inflate(LayoutInflater.from(parent.context))
        return AddProductViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AddProductViewHolder, position: Int) {
        val productItem = getItem(position)
        holder.itemView.setOnClickListener {
            callback(productItem)
        }

        holder.bind(productItem)

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