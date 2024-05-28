package com.toscano.proyecto1.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.toscano.proyecto1.data.network.entities.newsapi.allnews.Data
import com.toscano.proyecto1.databinding.ActivityRecyclerBinding
import com.toscano.proyecto1.logic.news.GetAllNewsCase
import com.toscano.proyecto1.ui.adapters.NewsAdapter
import com.toscano.proyecto1.ui.entities.NewsDataUI
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
        binding.rvTopNews.adapter = NewsAdapter(items) {showTitle(it)}
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

    //Funciones Lambda
    private fun showTitle(news: Data){

        /*
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("id", news.uuid)
        }
        startActivity(intent)
         */
        Snackbar.make(binding.rvTopNews, news.title.toString(), Snackbar.LENGTH_LONG).show()
    }
}


/*
class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    private fun initRecyclerView(items: List<NewsDataUI>){
        binding.rvTopNews.adapter = NewsAdapter(items) {showTitle(it)}
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

    //Funciones Lambda
    private fun showTitle(news: NewsDataUI){

        /*
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("id", news.id)
        }
        startActivity(intent)
         */
        Snackbar.make(binding.rvTopNews, news.title.toString(), Snackbar.LENGTH_LONG).show()
    }
}
 */