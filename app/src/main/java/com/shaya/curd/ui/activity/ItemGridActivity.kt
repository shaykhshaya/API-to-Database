package com.shaya.curd.ui.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.shaya.curd.databinding.GridItemActivityBinding
import com.shaya.curd.ui.AddProductGridAdapter
import com.shaya.curd.ui.viewmodel.ItemListViewModel
import com.shaya.curd.ui.viewmodel.ProductApiStatus
import com.shaya.curd.ui.viewmodel.ProductViewModel

class ItemGridActivity : AppCompatActivity() {


    private lateinit var roomViewModel: ItemListViewModel
    private lateinit var viewModel: ProductViewModel
    private lateinit var binding: GridItemActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = GridItemActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        roomViewModel = ViewModelProvider(this)[ItemListViewModel::class.java]

        binding.apply {
            viewModel = this@ItemGridActivity.viewModel
            lifecycleOwner = this@ItemGridActivity
        }

        binding.gridRecyclerView.adapter = AddProductGridAdapter {
            roomViewModel.addNewItem(it)
            finish()
        }

        binding.imageBack.setOnClickListener{
            onBackPressed()
        }



    }

}