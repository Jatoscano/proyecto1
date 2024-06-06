package com.toscano.proyecto1.data.network.endpoints

import com.toscano.proyecto1.data.network.entities.onenews.OneNewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UUIDNews {

    @GET("uuid/{uuid}")
    suspend fun getUUIDNews(@Path("uuid")uuid: String): Response<OneNewsData>
}