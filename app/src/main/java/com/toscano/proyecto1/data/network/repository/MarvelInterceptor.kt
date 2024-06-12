package com.toscano.proyecto1.data.network.repository

import okhttp3.Interceptor
import okhttp3.Response

class MarvelInterceptor(private val publicKey: String, private val ts: Int, private val hash: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val marvrelUrl  = chain.request().url.newBuilder().addQueryParameter("apiKey", publicKey).addQueryParameter("ts", ts.toString()).addQueryParameter("hash", hash).build()

        val marvelRequest = chain.request().newBuilder().url(marvrelUrl).build()

        val headers = marvelRequest.headers.newBuilder().add("Authentication", "Bearear$publicKey").build()

        return chain.proceed(marvelRequest)
    }
}