package com.toscano.proyecto1.data.network.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase {

    const val NEWS_API_KEY = "FNNteH2pWmIGXXKowBUgmUj2bZ0MhU1rrkBeOuME"

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
            .client(apiClient())
            .build()
    }

    private fun apiClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor(NewsInterceptor(RetrofitBase.NEWS_API_KEY)).build()

}