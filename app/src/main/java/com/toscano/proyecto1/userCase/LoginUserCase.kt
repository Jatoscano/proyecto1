package com.toscano.proyecto1.userCase

import com.toscano.proyecto1.data.UserClass
import com.toscano.proyecto1.data.repository.ListUsers

class LoginUserCase (var listUsers: ListUsers){
    //Casos de Uso
    operator fun invoke(user: String, password: String): Result<UserClass>{

        var us = listUsers.checkUserAndPassword(user, password)

        return if (us != null){
            //Result.success(true)
            Result.success(us)
        }

        else {
            Result.failure(Exception("Error de Usuario o Contraseña"))
        }
        /*
        return if (user == "admin" && password == "admin"){
            //Result.success(true)
            Result.success(45)
        }

        else {
            Result.failure(Exception("Error de Usuario o Contraseña"))
        }
         */
    }
}