package com.toscano.proyecto1.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.toscano.proyecto1.R
import com.toscano.proyecto1.data.local.database.entities.UsersDB
import com.toscano.proyecto1.data.local.repository.DataBaseRepository
import com.toscano.proyecto1.databinding.ActivityDataBaseBinding
import com.toscano.proyecto1.ui.core.Proyecto1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataBaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataBaseBinding
    private lateinit var connection: DataBaseRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initListeners()
    }

    private fun initVariables() {
        connection = Proyecto1.getDBConnection()
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {

            val user = binding.edTxtName.text.toString()
            val password = binding.edTxtPassword.text.toString()
            val userdB = UsersDB(name = user, password = password)

            lifecycleScope.launch (Dispatchers.IO){
                connection.getUsersDao().saveUsers(listOf(userdB))
            }
        }
    }



}