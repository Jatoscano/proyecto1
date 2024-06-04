package com.toscano.proyecto1.logic.news

import android.util.Log
import com.toscano.proyecto1.data.network.endpoints.NewsEndPoint
import com.toscano.proyecto1.data.network.entities.newsapi.allnews.Data
import com.toscano.proyecto1.data.network.repository.RetrofitBase
import com.toscano.proyecto1.ui.core.toNewsDataUI
import com.toscano.proyecto1.ui.entities.NewsDataUI

/*
class GetAllNewsCase {

    suspend operator fun invoke(): Result<List<Data>?>{

        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(NewsEndPoint::class.java)
            .getAllNews()

        return if (response.isSuccessful){

            val data = response.body()?.data
            Log.d("RSP", data.toString())
            Result.success(data)
        }

        else {
            Log.d("RSP", "La ejecucion fallo")
            Result.failure(Exception("La ejecucion fallo"))
        }
    }
}

*/
class GetAllNewsCase {

    suspend operator fun invoke(): Result<List<NewsDataUI>>{

        var item = mutableListOf<NewsDataUI>()

        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(NewsEndPoint::class.java)
            .getAllNews()

        return if (response.isSuccessful){

            response.body()?.data?.forEach{
            item.add(it.toNewsDataUI())
            }
            Log.d("RSP", item.toString())
            Result.success(item.toList())
        }

        else {
            Log.d("RSP", "La ejecucion fallo")
            Result.failure(Exception("La ejecucion fallo"))
        }
    }
}