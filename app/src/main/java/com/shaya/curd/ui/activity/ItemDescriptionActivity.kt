package com.shaya.curd.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shaya.curd.R
import com.shaya.curd.databinding.DescriptionItemActivityBinding
import com.shaya.curd.network.ProductItem
import com.shaya.curd.ui.viewmodel.ItemListViewModel
import com.shaya.curd.utils.KEY_ID
import com.shaya.curd.utils.loadImageByURL

class ItemDescriptionActivity : AppCompatActivity() {

    private lateinit var roomViewModel: ItemListViewModel

    private lateinit var binding: DescriptionItemActivityBinding

    lateinit var productItem: ProductItem

    private fun bind(productItem: ProductItem) {
        binding.apply {
            textViewTitle.text = productItem.title
            textViewDescription.text = productItem.description
            imageView.loadImageByURL(binding.imageView.context, productItem.image)
            deleteItem.setOnClickListener { showConfirmationDialog() }
            updateItem.setOnClickListener { editItem() }
            imageBack.setOnClickListener{ onBackPressed() }
        }

    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(this, R.style.Theme_MyApp_Dialog_Alert)
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage("Are you sure you want to delete?")
            .setCancelable(false)
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Yes") { _, _ ->
                deleteItem()
            }
            .show()
    }

    private fun deleteItem() {
        roomViewModel.deleteItem(productItem)
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DescriptionItemActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.extras?.getInt(KEY_ID)
        roomViewModel = ViewModelProvider(this)[ItemListViewModel::class.java]
        roomViewModel.retrieveItem(id!!).observe(this) { productItem ->
            if (productItem == null){
                finish()
                return@observe
            }
            this@ItemDescriptionActivity.productItem = productItem
            bind(productItem)
        }


    }


    private fun editItem() {
        val intent = Intent(this, UpdateActivity::class.java)
        intent.putExtra(KEY_ID, productItem.id)
        startActivity(intent)

    }


}