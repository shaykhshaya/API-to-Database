package com.shaya.curd.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import com.shaya.curd.data.ProductItemDao
import com.shaya.curd.data.ProductItemDatabase
import com.shaya.curd.databinding.GridItemActivityBinding
import com.shaya.curd.ui.viewmodel.ItemListViewModel
import com.shaya.curd.ui.viewmodel.ProductViewModel

class ItemGridActivity : AppCompatActivity() {

   /* private var roomViewModel: ItemListViewModel by viewModels {
        ItemListViewModel.ItemListViewModelFactory(ProductItemDatabase.getDatabase(this).productItemDao())
    }*/
    private lateinit var roomViewModel : ItemListViewModel

    private lateinit var viewModel: ProductViewModel

    private lateinit var binding:GridItemActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = GridItemActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        roomViewModel = ViewModelProvider(this)[ItemListViewModel::class.java]

       // roomViewModel = ItemListViewModelFactory(ProductItemDao)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.gridRecyclerView.adapter = AddProductGridAdapter{
            roomViewModel.addNewItem(it)
            finish()
        }








    }

}