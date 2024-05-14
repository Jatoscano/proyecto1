package com.toscano.proyecto1.data.local

import android.util.Log

data class UserClass(var id: Int, var name: String, var password: String){

    init {
        Log.d("DATA", "Se contruye una nueva instancia de Usuario")
    }
}
