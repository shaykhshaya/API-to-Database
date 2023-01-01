package com.shaya.curd.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.curd.databinding.ListItemBinding
import com.shaya.curd.network.ProductItem
import com.shaya.curd.ui.AddProductGridAdapter
import com.shaya.curd.utils.loadImageByURL
import java.util.TreeMap

class ItemListAdapter(private val onItemClicked : (ProductItem) -> Unit): ListAdapter<ProductItem, ItemListAdapter.ItemViewHolder>(DiffCall) {

    class ItemViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(productItem: ProductItem){
            binding.apply {
                textViewTitle.text = productItem.title
                textViewDescription.text = productItem.description
                imageView.loadImageByURL(binding.imageView.context, productItem.image)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }


    companion object {
        private val DiffCall = object : DiffUtil.ItemCallback<ProductItem>() {
            override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
                return oldItem.title == oldItem.title
            }
        }
    }



}