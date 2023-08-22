package com.naman.ezobooksassignmentfetchapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://db.ezobooks.in/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: EzoBookItemsAPi by lazy {
        retrofit.create(EzoBookItemsAPi::class.java)
    }
}