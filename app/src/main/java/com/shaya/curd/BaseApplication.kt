package com.shaya.curd

import android.app.Application
import com.shaya.curd.data.ProductItemDatabase

class BaseApplication: Application() {
    val database: ProductItemDatabase by lazy {
        ProductItemDatabase.getDatabase(this)
    }
}