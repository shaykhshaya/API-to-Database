package com.shaya.curd.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.shaya.curd.R
import com.shaya.curd.databinding.ActivityUpdateBinding
import com.shaya.curd.network.ProductItem
import com.shaya.curd.ui.viewmodel.ItemListViewModel
import com.shaya.curd.utils.loadImageByURL

class UpdateActivity : AppCompatActivity() {

    private lateinit var roomViewModel : ItemListViewModel
    lateinit var binding: ActivityUpdateBinding
    private lateinit var item: ProductItem
    private var id: Int? = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent?.extras?.getInt("product_id")

        roomViewModel = ViewModelProvider(this)[ItemListViewModel::class.java]



        roomViewModel.retrieveItem(id!!).observe(this){selectedItem ->
            item = selectedItem
            bind(item)

        }
    }


    private fun bind(productItem: ProductItem){
        binding.apply {
            editTitle.setText(productItem.title, TextView.BufferType.SPANNABLE)
            editDescription.setText(productItem.description, TextView.BufferType.SPANNABLE)
            imageView.loadImageByURL(this@UpdateActivity, productItem.image)

            btnUpdate.setOnClickListener {
                roomViewModel.updateItem( id!!, binding.editTitle.text.toString(), binding.editDescription.text.toString(), productItem.image)

                val intent = Intent(this@UpdateActivity, ItemListActivity::class.java)
                startActivity(intent)


            }
        }
    }

}