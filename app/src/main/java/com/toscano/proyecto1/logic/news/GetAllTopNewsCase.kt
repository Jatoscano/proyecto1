package com.toscano.proyecto1.logic.news

import android.util.Log
import com.toscano.proyecto1.data.network.endpoints.NewsEndPoint
import com.toscano.proyecto1.data.network.repository.RetrofitBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class GetAllTopNewsCase {
    suspend operator fun invoke(){

        //val token = "919689e8-bcd1-45b7-988f-8de37847ff6a"
        //val key = "FNNteH2pWmIGXXKowBUgmUj2bZ0MhU1rrkBeOuME"

        withContext(Dispatchers.IO){

            var response = RetrofitBase.returnBaseRetrofitNews()
                .create(NewsEndPoint::class.java)
                .getAllTopNews()

            return@withContext if (response.isSuccessful){
                val data = response.body()?.data
                Log.d("RSP", data.toString())
                true
            }
            else{
                Log.d("RSP", "La ejecucion fallo")
                false
            }
        }
    }
}