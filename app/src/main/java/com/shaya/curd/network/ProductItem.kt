package com.shaya.curd.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product_database")
data class ProductItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name= "Title")
    val title: String,
    @ColumnInfo(name= "Description")
    val description: String,
    @ColumnInfo(name= "ImageSource")
    val image: String
)
