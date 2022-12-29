package com.shaya.curd.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.shaya.curd.databinding.ActivityAddProductBinding

class AddProduct : AppCompatActivity() {
    private lateinit var viewModel: ProductViewModel
    private lateinit var binding:ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.addRecyclerView.adapter = AddProductGridAdapter()



    }

}