package com.toscano.proyecto1.data.network.repository

import okhttp3.Interceptor
import okhttp3.Response

class NewsInterceptor(private val apiKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        /*
        val request  = chain.request()

        val newsUrl = request.url().newBuilder().addQueryParameter("api_token", apiKey).build()

        val newsRequest = request.newBuilder().url(newsUrl).build()


        val error = chain.proceed(newsRequest)

        when(error.code()){
            409 -> {
                val error1 = request.newBuilder().header("dsahdkj", "sckdhsfkjs").build()
                val token = chain.proceed(error1).body().toString()
            }

            else -> {TODO("Codigo funcional para solicitudes normales") }
        }
         */

        val newsUrl  = chain.request().url().newBuilder().addQueryParameter("api_token", apiKey).build()

        val newsRequest = chain.request().newBuilder().url(newsUrl).build()

        return chain.proceed(newsRequest)

    }
}