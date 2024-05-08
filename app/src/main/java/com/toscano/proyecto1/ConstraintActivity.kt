package com.toscano.proyecto1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.toscano.proyecto1.databinding.ActivityConstraintBinding

class ConstraintActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstraintBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConstraintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
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

    private fun initListeners(){

        binding.btnConstLogin.setOnClickListener {
            if (binding.edtxtConstUser.text.toString() == "admin" && binding.edtxtConstPassword.text.toString() == "admin"){
                Snackbar.make(binding.edtxtConstUser, "Bienvenido", Snackbar.LENGTH_SHORT).show()

            }

            else{
                Snackbar.make(binding.edtxtConstUser, "Hay un error en el usuario", Snackbar.LENGTH_SHORT).show()

            }
        }

        binding.btnConstExit.setOnClickListener{

            var a = Intent(this,MainActivity::class.java)

            startActivity(a)
        }

        Log.d("UCE", "Metodo onCreate")
    }
}