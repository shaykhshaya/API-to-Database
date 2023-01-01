package com.shaya.curd.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shaya.curd.databinding.ListItemActivityBinding
import com.shaya.curd.ui.adapter.ItemListAdapter
import com.shaya.curd.ui.viewmodel.ItemListViewModel

class ItemListActivity : AppCompatActivity() {

    private lateinit var binding: ListItemActivityBinding
    private lateinit var roomViewModel : ItemListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListItemActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomViewModel = ViewModelProvider(this)[ItemListViewModel::class.java]

        binding.floatingAddBtn.setOnClickListener {
            val intent = Intent(this, ItemGridActivity::class.java)
            startActivity(intent)
        }

        val adapter = ItemListAdapter {
            val intent = Intent(this, ItemDescriptionActivity::class.java )
            intent.putExtra("data", it.id)
            startActivity(intent)

        }
        binding.listRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.listRecyclerView.adapter = adapter

        roomViewModel.allItems.observe(this){
            it.let {
                adapter.submitList(it)
            }

        }








    }
}