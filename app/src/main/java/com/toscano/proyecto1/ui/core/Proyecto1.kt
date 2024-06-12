package com.toscano.proyecto1.ui.core

import android.app.Application
import androidx.room.Room
import com.toscano.proyecto1.data.local.repository.DataBaseRepository

class Proyecto1: Application(){

    override fun onCreate() {
        super.onCreate()

        //Conexion de la Base de Datos

        dbConnection = Room.databaseBuilder(applicationContext, DataBaseRepository::class.java, "Datos").build()

        /*
          fun getConnection() : DataBaseRepository{

              return Room.databaseBuilder(applicationContext, DataBaseRepository::class.java, "Datos").build()
          }
         */

    }

    companion object{

        private var dbConnection: DataBaseRepository? = null

        fun getDBConnection(): DataBaseRepository{

            return dbConnection!!
        }
    }
}