package com.shaya.curd.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shaya.curd.R
import com.shaya.curd.databinding.DescriptionItemActivityBinding
import com.shaya.curd.network.ProductItem
import com.shaya.curd.ui.viewmodel.ItemListViewModel
import com.shaya.curd.utils.loadImageByURL

class ItemDescriptionActivity : AppCompatActivity() {

    private lateinit var roomViewModel : ItemListViewModel

    private lateinit var binding: DescriptionItemActivityBinding

    lateinit var productItem: ProductItem

    private fun bind(productItem: ProductItem){
        binding.apply {
            textViewTitle.text = productItem.title
            textViewDescription.text = productItem.description
            imageView.loadImageByURL(binding.imageView.context, productItem.image)
            deleteItem.setOnClickListener { showConfirmationDialog() }
            updateItem.setOnClickListener { editItem() }
        }
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage("Are you sure you want to delete?")
            .setCancelable(false)
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Yes") { _, _ ->
                deleteItem()
            }
            .show()
    }

    private fun deleteItem(){
        roomViewModel.deleteItem(productItem)
        val intent = Intent(this, ItemListActivity::class.java)
        startActivity(intent)
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DescriptionItemActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.extras?.getInt("data")
        roomViewModel = ViewModelProvider(this)[ItemListViewModel::class.java]
        roomViewModel.retrieveItem(id!!).observe(this){ProductItem ->
            productItem = ProductItem
            bind(productItem)
        }



    }


    private fun editItem(){
        val intent = Intent(this, UpdateActivity::class.java)
       intent.putExtra("product_id", productItem.id)
        startActivity(intent)

    }






}