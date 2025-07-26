package com.example.studiiaitrail2.ui

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/") // Must end with '/'
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: SearchApi = retrofit.create(SearchApi::class.java)
}


