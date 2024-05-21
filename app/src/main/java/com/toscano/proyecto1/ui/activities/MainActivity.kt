package com.toscano.proyecto1.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.toscano.proyecto1.data.local.repository.ListUsers
import com.toscano.proyecto1.databinding.ActivityMainBinding
import com.toscano.proyecto1.logic.jsonplace.GetAllUsersUserCase
import com.toscano.proyecto1.logic.news.GetAllTopNewsCase
import com.toscano.proyecto1.logic.usercases.LoginUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        binding.btnLogin.setOnClickListener {


            var loginUserCase = LoginUserCase(ListUsers())

            var result = loginUserCase(binding.edtxtUser.text.toString(), binding.edtxtPass.text.toString())

            result.onSuccess {user ->

                var intentToConstraintActivity = Intent(this, ConstraintActivity::class.java)
                    .apply {
                        putExtra("IdUser", user.id)
                    }
                startActivity(intentToConstraintActivity)
            }

            result.onFailure {
                Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
            }

            /*
            //Manejo de Expersiones Lambda
            result.onSuccess {

                var intentToConstraintActivity = Intent(this, ConstraintActivity::class.java)
                intentToConstraintActivity.putExtra("IdUser", it)
                startActivity(intentToConstraintActivity)
            }

            result.onFailure {
                Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
             */

        }
        binding.btnCorr.setOnClickListener {
            var intentCorroutineActivity = Intent(this, CorroutineActivity::class.java)

            startActivity(intentCorroutineActivity)
        }

        binding.btnRecycler.setOnClickListener {
            var intentRecyclerActivity = Intent(this, RecyclerActivity::class.java)

            startActivity(intentRecyclerActivity)
        }

        Log.d("UCE", "Metodo onCreate")
    }
}