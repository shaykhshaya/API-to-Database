package com.shaya.curd.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.shaya.curd.network.ProductItem
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductItemDao {

    @Query("SELECT * FROM product_database ORDER BY id")
    fun getProducts(): Flow<List<ProductItem>>

    @Query("SELECT * FROM product_database WHERE id = :id")
    fun getProduct(id: Int): Flow<ProductItem>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productItem: ProductItem)

    @Update
    suspend fun update(productItem: ProductItem)

    @Delete
    suspend fun delete(productItem: ProductItem)



}