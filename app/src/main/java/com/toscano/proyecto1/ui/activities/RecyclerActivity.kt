package com.toscano.proyecto1.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.toscano.proyecto1.R
import com.toscano.proyecto1.data.network.entities.newsapi.topnews.Data
import com.toscano.proyecto1.databinding.ActivityRecyclerBinding
import com.toscano.proyecto1.logic.news.GetAllTopNewsCase
import com.toscano.proyecto1.ui.adapters.NewsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding

    private var item  = ArrayList<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = initData()

        initRecyclerView(item)

    }

    private fun initRecyclerView(items: List<Data>){
        binding.rvTopNews.adapter = NewsAdapter(item)
        binding.rvTopNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initData(): List<Data>{

        lifecycleScope.launch(Dispatchers.Main) {
            val resultItem = withContext(Dispatchers.IO){
                GetAllTopNewsCase().invoke()
            }

            resultItem.onSuccess { item.addAll(it!!.toList()) }
            resultItem.onFailure { item.addAll(emptyList()) }
        }

    }
}