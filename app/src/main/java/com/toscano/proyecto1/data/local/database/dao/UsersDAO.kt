package com.toscano.proyecto1.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.toscano.proyecto1.data.local.database.entities.UsersDB

@Dao
interface UsersDAO {

    @Query("select * from users")
    fun getAllUsers(): List<UsersDB>

    @Query("select * from users where id_user = :id")
    fun getOneUser(id: Int): UsersDB

    @Insert
    fun saveUsers(user: List<UsersDB>)

    @Delete
    fun deleteUser(user: UsersDB)
}