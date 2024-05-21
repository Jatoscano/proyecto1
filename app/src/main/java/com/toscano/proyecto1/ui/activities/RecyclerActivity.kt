package com.toscano.proyecto1.ui.activities

import android.os.Bundle
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

        val items = initData()

        initRecyclerView(items)

    }

    private fun initRecyclerView(items: List<Data>){
        binding.rvTopNews.adapter = NewsAdapter(items)
        binding.rvTopNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initData(): List<Data>{

        var items = ArrayList<Data>()
        lifecycleScope.launch(Dispatchers.Main) {
            val resultItem = withContext(Dispatchers.IO){ GetAllNewsCase().invoke() }

            resultItem.onSuccess { items.addAll(it!!.toList()) }
            resultItem.onFailure { items.addAll(emptyList()) }
        }
        return items
    }
}