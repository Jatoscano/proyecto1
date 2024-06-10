package com.toscano.proyecto1.logic.news

import android.util.Log
import com.toscano.proyecto1.data.network.endpoints.MarvelEndPoint
import com.toscano.proyecto1.data.network.entities.marvel.MarvelChars
import com.toscano.proyecto1.data.network.repository.RetrofitBase

class GetMarvelCharUseCase {

    suspend operator fun invoke(): Result<MarvelChars?>{

        var response = RetrofitBase.returnBaseRetrofitMarvel()
            .create(MarvelEndPoint::class.java)
            .getAllCharacters(2)

        return if (response.isSuccessful){

            val x = response.body()
            Log.d("RSP", x.toString())
            Result.success(x)
        }

        else {
            Log.d("RSP", "La ejecucion fallo")
            Result.failure(Exception("La ejecucion fallo"))
        }
    }
}