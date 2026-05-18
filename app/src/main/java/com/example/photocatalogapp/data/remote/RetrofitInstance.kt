package com.example.photocatalogapp.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .header("User-Agent", "PhotoCatalogApp Android")
                .header("Accept", "application/json")
                .build()

            chain.proceed(request)
        }
        .build()

    val api: PhotoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://shikimori.one/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoApi::class.java)
    }
}