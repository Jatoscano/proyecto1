package com.toscano.proyecto1.data.network.endpoints

import com.toscano.proyecto1.data.network.entities.marvel.MarvelChars
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelEndPoint {

    @GET("v1/public/characters")
    suspend fun getAllCharacters(//@Query("hash") hash: String,
                                 //@Query("apiKey") apiKey: String,
                                 //@Query("ts") ts: String,
                                 @Query("limit") limit: Int): Response<MarvelChars>
}