package com.toscano.proyecto1.logic.news

import android.util.Log
import com.toscano.proyecto1.data.network.endpoints.UUIDNews
import com.toscano.proyecto1.data.network.entities.onenews.OneNewsData
import com.toscano.proyecto1.data.network.repository.RetrofitBase

class GetOneNewsCase {

    suspend operator fun invoke(uuid: String): Result<OneNewsData?>{

        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(UUIDNews::class.java)
            .getUUIDNews(uuid)

        return if (response.isSuccessful){

            val data = response.body()
            Log.d("RSP", data.toString())
            Result.success(data)
        }

        else {
            Log.d("RSP", "La ejecucion fallo")
            Result.failure(Exception("La ejecucion fallo"))
        }
    }
}