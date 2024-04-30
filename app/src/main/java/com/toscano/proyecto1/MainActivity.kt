package com.toscano.proyecto1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.toscano.proyecto1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Inicializacion de Binding
    private lateinit var binding: ActivityMainBinding

    /*
    //Inicializacion de Variables
    private lateinit var edtxtUser : EditText
    private lateinit var edtxtPassword : EditText
    private lateinit var btnLogin :Button

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtxtUser = findViewById<EditText>(R.id.edtxt_user)
        edtxtPassword = findViewById<EditText>(R.id.edtxt_pass)
        btnLogin = findViewById<Button>(R.id.btn_login)
         */

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
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
         */

        binding.btnLogin.setOnClickListener {
            if (binding.edtxtUser.text.toString() == "admin" && binding.edtxtPass.text.toString() == "admin"){
                Snackbar.make(binding.edtxtUser, "Bienvenido", Snackbar.LENGTH_SHORT).show()
                //Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
            }


            else{
                Snackbar.make(binding.edtxtUser, "Hay un error en el usuario", Snackbar.LENGTH_SHORT).show()
                //Toast.makeText(this, "Hay un error en el usuario", Toast.LENGTH_SHORT).show()
            }
        }

        Log.d("UCE", "Metodo onCreate")
    }

    //Se generar cuando se inicia o se necesita
    override fun onStart() {
        super.onStart()
        Log.d("UCE", "Metodo onStart")
    }

    //Se genera una sola vez
    override fun onResume() {
        super.onResume()
        Log.d("UCE", "Metodo onResume")
    }
}