package com.shaya.curd.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaya.curd.network.ProductApi
import com.shaya.curd.network.ProductItem
import kotlinx.coroutines.launch


enum class ProductApiStatus {LOADING, ERROR, DONE}

    class ProductViewModel : ViewModel() {

        private val _status = MutableLiveData<ProductApiStatus>()
        val status: LiveData<ProductApiStatus> = _status

        private val _products = MutableLiveData<List<ProductItem>>()
        val products:LiveData<List<ProductItem>> = _products

        init {
            getProductsList()
        }

        private fun getProductsList(){
            viewModelScope.launch {
                _status.value = ProductApiStatus.LOADING
                try {
                    _products.value = ProductApi.retrofitService.getProducts()
                    _status.value = ProductApiStatus.DONE
                }
                catch (e:Exception){
                    _status.value = ProductApiStatus.ERROR
                    _products.value = listOf()
                }
            }
        }


    }















