package com.toscano.proyecto1.data.network.endpoints

import com.toscano.proyecto1.data.network.entities.UsersAPI
import retrofit2.Response
import retrofit2.http.GET

interface UsersEndPoint {

    @GET("users")
    fun getAllUsersEndPoint(): Response<List<UsersAPI>>
}