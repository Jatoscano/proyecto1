package com.toscano.proyecto1.data.network.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase {

    const val NEWS_API_KEY = "120b9189d58c32e4c95c26f28e8b17f7f641cd2c1ad94e62e8de29be8d2a036a73179c47430"

    const val MARVEL_API_KEY = "b9189d58c32e4c95c26f28e8b17f7f64"

    const val TS_MARVEL =  120

    const val  HASH_MARVEL= "e76065b072067fd94d585f078545ed0a"

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

    fun returnBaseRetrofitMarvel(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:433/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(marvelApiClient())
            .build()
    }

    private fun apiClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor(NewsInterceptor(RetrofitBase.NEWS_API_KEY)).build()

    private fun marvelApiClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor(MarvelInterceptor(MARVEL_API_KEY, TS_MARVEL, HASH_MARVEL)).build()



}