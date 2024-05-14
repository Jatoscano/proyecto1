package com.toscano.proyecto1.data.local.repository

import com.toscano.proyecto1.data.local.UserClass

class ListUsers {
    /*
    private fun getUsers(): List<UserClass>{
        return listOf(
            UserClass(1, "admin", "admin"),
            UserClass(2, "juan", "juan"),
            UserClass(3,"andres", "andres")
        )
    }
     */

    var listUser = listOf(
    UserClass(1, "admin", "admin"),
    UserClass(2, "juan", "juan"),
    UserClass(3,"andres", "andres")
    )

    fun checkUserAndPassword(user: String, password: String): UserClass?{

        var usReturn: UserClass? = null

        listUser.forEach{
                us ->
            if (us.name == user && us.password == password){
                usReturn = us
            }
        }

        return usReturn
        /*
        var users = getUsers()

        var usReturn: UserClass? = null
        users.forEach{
            us ->
            if (us.name == user && us.password == password){
                usReturn = us
            }
        }

        return usReturn
         */
    }

    fun checkUserAndPassword2(user: String, password: String): UserClass?{

        return listUser.filter {
                us -> us.name == user && us.password == password
        }.first()

        /*
        var users = getUsers()

        return users.filter {
            us -> us.name == user && us.password == password
        }.first()
         */

    }

    fun checkUserAndPassword3(user: String, password: String): UserClass?{

        var us = UserClass(0, user, password)

        return listUser.filter {
            it == us
        }.first()
        /*
        var users = getUsers()
        var us = UserClass(0, user, password)

        return users.filter {
                it == us
        }.first()
         */

    }
}