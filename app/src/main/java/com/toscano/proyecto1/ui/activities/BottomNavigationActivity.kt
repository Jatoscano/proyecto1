package com.toscano.proyecto1.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.toscano.proyecto1.R
import com.toscano.proyecto1.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()

    }

    private fun initListeners(){

        binding.standardBottomSheet.visibility = View.INVISIBLE

        binding.bottomNavigationTest.setOnItemSelectedListener { item ->

            //val standardBottomSheet = findViewById<FrameLayout>(R.id.standard_bottom_sheet)
            //val standardBottomSheetBehavior = BottomSheetBehavior.from(standardBottomSheet)

            when(item.itemId) {
                R.id.favoriteItem -> {
                    binding.txtName.text = "Juan"
                    binding.txtPass.text = "12345"
                    binding.standardBottomSheet.visibility = View.VISIBLE
                    //standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    binding.standardBottomSheet.setOnClickListener {
                        binding.standardBottomSheet.visibility = View.INVISIBLE
                        //standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                    Snackbar.make(binding.bottomNavigationTest, "Listado de Favoritas", Snackbar.LENGTH_LONG).show()
                    true
                }
                R.id.presentItem -> {
                    binding.txtName.text = "Juan"
                    binding.txtPass.text = "12345"
                    binding.standardBottomSheet.visibility = View.VISIBLE
                    binding.standardBottomSheet.setOnClickListener { binding.standardBottomSheet.visibility = View.INVISIBLE }
                    Snackbar.make(binding.bottomNavigationTest, "Listado de Recientes", Snackbar.LENGTH_LONG).show()
                    true
                }
                R.id.recommendItem -> {
                    binding.txtName.text = "Juan"
                    binding.txtPass.text = "12345"
                    binding.standardBottomSheet.visibility = View.VISIBLE
                    binding.standardBottomSheet.setOnClickListener { binding.standardBottomSheet.visibility = View.INVISIBLE }
                    Snackbar.make(binding.bottomNavigationTest, "Listado de Recomendadas", Snackbar.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }
    }

}