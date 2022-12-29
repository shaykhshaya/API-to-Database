package com.shaya.curd.ui.viewmodel

import androidx.lifecycle.*
import com.shaya.curd.data.ProductItemDao
import com.shaya.curd.network.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemListViewModel(private val productItemDao: ProductItemDao): ViewModel() {


    val allItems: LiveData<List<ProductItem>> = productItemDao.getProducts().asLiveData()

    fun retrieveItem(id: Int): LiveData<ProductItem>{
        return productItemDao.getProduct(id).asLiveData()
    }

    fun addItem(title: String, description: String, image: String){
        val item = ProductItem(title = title, description = description, image = image)
        viewModelScope.launch {
            productItemDao.insert(item)
        }
    }

    fun updateItem(id: Int, title: String, description: String, image: String){
        val item = ProductItem(id = id, title = title, description = description, image = image)
        viewModelScope.launch {
            productItemDao.update(item)
        }
    }

    fun deleteItem(item: ProductItem){
        viewModelScope.launch(Dispatchers.IO){
            productItemDao.delete(item)
        }
    }


}


class ItemListViewModelFactory(private val productItemDao: ProductItemDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ItemListViewModel::class.java)){
            @Suppress("UNCHECKED CAST")
            return ItemListViewModel(productItemDao) as T}
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
        }
    }
