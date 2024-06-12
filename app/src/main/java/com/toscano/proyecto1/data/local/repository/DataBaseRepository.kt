package com.toscano.proyecto1.data.local.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toscano.proyecto1.data.local.database.dao.UsersDAO
import com.toscano.proyecto1.data.local.database.entities.UsersDB

@Database(entities = [UsersDB::class], version = 1)

//Las versiones son para actualizacion de la Base de Datos

abstract class DataBaseRepository: RoomDatabase() {

    abstract fun getUsersDao(): UsersDAO
}