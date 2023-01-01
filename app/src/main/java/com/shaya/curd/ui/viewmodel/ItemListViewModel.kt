package com.shaya.curd.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.shaya.curd.BaseApplication
import com.shaya.curd.data.ProductItemDao
import com.shaya.curd.network.ProductItem
import kotlinx.coroutines.launch

class ItemListViewModel : ViewModel() {

    private val productItemDao: ProductItemDao = BaseApplication.database.productItemDao()

    val allItems: LiveData<List<ProductItem>> = productItemDao.getProducts().asLiveData()

    fun addNewItem(productItem: ProductItem) {
        insertItem(productItem)
    }

    private fun insertItem(productItem: ProductItem) {
        viewModelScope.launch {
            productItemDao.insert(productItem)
        }
    }

    fun updateItem(
        itemId: Int,
        title: String,
        description: String,
        imageUrl: String
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, title, description, imageUrl)
        updateItem(updatedItem)
    }

    private fun getUpdatedItemEntry(
        itemId: Int,
        title: String,
        description: String,
        imageUrl: String
    ): ProductItem {
        return ProductItem(
            id = itemId,
            title = title,
            description = description,
            image = imageUrl
        )
    }

    private fun updateItem(productItem: ProductItem) {
        viewModelScope.launch {
            productItemDao.update(productItem)
        }
    }


    fun retrieveItem(id: Int): LiveData<ProductItem> {
        return productItemDao.getProduct(id).asLiveData()
    }

    fun deleteItem(productItem: ProductItem) {
        viewModelScope.launch {
            productItemDao.delete(productItem)
        }
    }

}






