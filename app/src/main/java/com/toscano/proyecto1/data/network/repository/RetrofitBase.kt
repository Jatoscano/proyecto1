package com.toscano.proyecto1.data.network.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase {

    //Patron de Diseño Builder
    //No cambia y sea inmutable
    fun returnBaseRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun returnBaseRetrofitNews(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.thenewsapi.com/v1/news/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}