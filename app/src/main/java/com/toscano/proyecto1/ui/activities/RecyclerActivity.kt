package com.toscano.proyecto1.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.toscano.proyecto1.data.network.entities.newsapi.allnews.Data
import com.toscano.proyecto1.databinding.ActivityRecyclerBinding
import com.toscano.proyecto1.logic.news.GetAllNewsCase
import com.toscano.proyecto1.ui.adapters.NewsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    private fun initRecyclerView(items: List<Data>){
        binding.rvTopNews.adapter = NewsAdapter(items)
        binding.rvTopNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initData(){

        //Progress Indicators
        binding.prgbarData.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {
            val resultItem = GetAllNewsCase().invoke()

            withContext(Dispatchers.Main){

                binding.prgbarData.visibility = View.INVISIBLE

                resultItem.onSuccess { initRecyclerView(it!!.toList()) }
                resultItem.onFailure { initRecyclerView(emptyList()) }
            }

        }
    }
}