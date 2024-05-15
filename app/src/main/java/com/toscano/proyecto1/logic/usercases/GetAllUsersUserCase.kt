package com.toscano.proyecto1.logic.usercases

import android.provider.ContactsContract.Data
import android.util.Log
import com.toscano.proyecto1.data.network.endpoints.UsersEndPoint
import com.toscano.proyecto1.data.network.repository.RetrofitBase

class GetAllUsersUserCase {

    suspend operator fun invoke(){

        //Nos devuelve el object base
        val call = RetrofitBase.returnBaseRetrofit()
        //Nos devuelve el endpoint - servicio
        val service  = call.create(UsersEndPoint::class.java)
        //Nos devuelve la respuesta
        val response = service.getAllUsersEndPoint()

        if (response.isSuccessful){

            val body = response.body()
            Log.d("RSP", body.toString())
        }
        else{
            Log.d("RSP", "La ejecucion fallo")
        }
    }
}
