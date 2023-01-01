package com.shaya.curd.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.shaya.curd.BaseApplication
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://fakestoreapi.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(ChuckerInterceptor(BaseApplication.instance.applicationContext))
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .baseUrl(BASE_URL)
    .build()

interface ProductApiService {
    @GET("products")
    suspend fun getProducts():List<ProductItem>
}

object ProductApi {
    val retrofitService: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }
}




