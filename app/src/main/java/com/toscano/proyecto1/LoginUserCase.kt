package com.toscano.proyecto1

class LoginUserCase {
    //Casos de Uso
    operator fun invoke(user: String, password: String): Result<Int>{

        return if (user == "admin" && password == "admin"){
            Result.success(true)
            Result.success(45)
        }

        else {
            Result.failure(Exception("Error de Usuario o Contraseña"))
        }
    }
}