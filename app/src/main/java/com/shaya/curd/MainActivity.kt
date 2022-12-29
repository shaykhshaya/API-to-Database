package com.shaya.curd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shaya.curd.databinding.ActivityMainBinding
import com.shaya.curd.ui.AddProduct

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingAddBtn.setOnClickListener {
            val intent = Intent(this, AddProduct::class.java)
            startActivity(intent)
        }
    }
}