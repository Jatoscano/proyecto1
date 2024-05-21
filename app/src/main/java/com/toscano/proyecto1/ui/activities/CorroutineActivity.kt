package com.toscano.proyecto1.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.toscano.proyecto1.R
import com.toscano.proyecto1.databinding.ActivityCorroutineBinding
import com.toscano.proyecto1.logic.news.GetAllTopNewsCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CorroutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCorroutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCorroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCorr()

    }

    private fun initCorr(){
        //Modo de Correr las Corrutinas
        /*
        lifecycleScope.launch (Dispatchers.IO){
            GetAllUsersUserCase().invoke()
        }
         */

        lifecycleScope.launch (Dispatchers.IO){
            GetAllTopNewsCase().invoke()
        }
    }
}