package com.toscano.proyecto1.data.network.endpoints


import com.toscano.proyecto1.data.network.entities.newsapi.allnews.AllNewsAPI
import com.toscano.proyecto1.data.network.entities.newsapi.topnews.TopNewsAPI
import retrofit2.Response
import retrofit2.http.GET

interface NewsEndPoint {

    /*
    @GET("top")
    suspend fun getAllTopNews(@Query("api_token") apiToken: String): Response<TopNewsAPI?>
     */

    @GET("top/{user_id}")
    suspend fun getAllTopNews(): Response<TopNewsAPI?>

    @GET("all")
    suspend fun getAllNews(): Response<AllNewsAPI?>
}