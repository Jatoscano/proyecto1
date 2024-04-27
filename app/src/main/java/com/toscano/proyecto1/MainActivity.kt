package com.toscano.proyecto1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var edtxtUser = findViewById<EditText>(R.id.edtxtUser)
        var edtxtPassword = findViewById<EditText>(R.id.edtxtPass)
        var btnLogin = findViewById<Button>(R.id.btn)

        btnLogin.setOnClickListener{
            if (edtxtUser.text.toString() == "admin" && edtxtPassword.text.toString() == "admin"){
                Snackbar.make(edtxtUser, "Bienvenido", Snackbar.LENGTH_SHORT).show()
                //Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
            }


            else{
                Snackbar.make(edtxtUser, "Hay un error en el usuario", Snackbar.LENGTH_SHORT).show()
                //Toast.makeText(this, "Hay un error en el usuario", Toast.LENGTH_SHORT).show()
        }
        }
    }
}