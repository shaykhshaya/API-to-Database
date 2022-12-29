package com.shaya.curd.ui.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.curd.databinding.ActivityMainBinding
import com.shaya.curd.network.ProductItem

class ItemListAdapter(private val onClickListener: (ProductItem)-> Unit): ListAdapter<ProductItem,ItemListAdapter.ItemViewHolder>(DiffCallback) {

    class ItemViewHolder(private var binding: ActivityMainBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(productItem: ProductItem){
            binding.item = productItem
                binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ProductItem>(){
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
           return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = ActivityMainBinding.inflate(LayoutInflater.from(parent.context))
        return ItemViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener(item)
        }
        holder.bind(item)
    }


}