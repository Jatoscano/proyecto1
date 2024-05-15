package com.toscano.proyecto1.data.network.endpoints

import com.toscano.proyecto1.data.network.entities.jsonplace.UsersAPI
import retrofit2.Response
import retrofit2.http.GET

interface UsersEndPoint {

    @GET("users")
    suspend fun getAllUsersEndPoint(): Response<List<UsersAPI?>>

}