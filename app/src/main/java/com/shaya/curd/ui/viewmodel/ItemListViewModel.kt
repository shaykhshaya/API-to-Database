package com.shaya.curd.ui.viewmodel

import android.content.ClipData
import androidx.lifecycle.*
import com.shaya.curd.BaseApplication
import com.shaya.curd.data.ProductItemDao
import com.shaya.curd.data.ProductItemDatabase
import com.shaya.curd.network.ProductItem
import kotlinx.coroutines.launch

class ItemListViewModel: ViewModel() {

    private val productItemDao: ProductItemDao = BaseApplication.database.productItemDao()

    val allItems: LiveData<List<ProductItem>> = productItemDao.getProducts().asLiveData()

    fun addNewItem(productItem: ProductItem){
        insertItem(productItem)
    }

    private fun insertItem(productItem: ProductItem){
        viewModelScope.launch {
            productItemDao.insert(productItem)
        }
    }

   /* fun addNewItem(title: String, description: String, imageUrl: String){
        val newItem = ProductItem(
            title = title, description = description, image = imageUrl
        )
        insertItem(newItem)
    }*/







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



    private fun updateItem(productItem: ProductItem){
        viewModelScope.launch {
            productItemDao.update(productItem)
        }
    }


    fun retrieveItem(id: Int): LiveData<ProductItem>{
        return productItemDao.getProduct(id).asLiveData()
    }

    fun deleteItem(productItem: ProductItem){
        viewModelScope.launch {
            productItemDao.delete(productItem)
        }
    }

   /* //to instantiate the InventoryViewModel instance
    class ItemListViewModelFactory(private val itemDao: ProductItemDao): ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ItemListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ItemListViewModel(itemDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }*/


}






