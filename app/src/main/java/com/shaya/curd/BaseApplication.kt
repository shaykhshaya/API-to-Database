package com.shaya.curd

import android.app.Application
import com.shaya.curd.data.ProductItemDatabase

class BaseApplication: Application() {

    companion object {
        lateinit var instance: BaseApplication
        lateinit var database: ProductItemDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = ProductItemDatabase.getDatabase(this)
    }
}