package com.toscano.proyecto1.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.toscano.proyecto1.R
import com.toscano.proyecto1.databinding.FragmentListNewsBinding
import com.toscano.proyecto1.logic.news.GetAllNewsCase
import com.toscano.proyecto1.ui.activities.DetailActivity
import com.toscano.proyecto1.ui.adapters.NewsAdapter
import com.toscano.proyecto1.ui.entities.NewsDataUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListNewsFragment : Fragment() {

    private lateinit var binding: FragmentListNewsBinding
    private var items: MutableList<NewsDataUI> = mutableListOf()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListNewsBinding.bind(inflater.inflate(R.layout.fragment_list_news, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariables()
        initListeners()
        initData()
    }

    private fun initVariables(){
        newsAdapter = NewsAdapter({descriptionItem(it)}, {deleteItem(it)})
        binding.rvTopNews.adapter = newsAdapter
        //Vista de Contenido en Forma de Carrusel
        //binding.rvTopNews.layoutManager = CarouselLayoutManager()
        //Vista de Contenido en forma de cuadros
        //binding.rvTopNews.layoutManager = GridLayoutManager(this, 2)
        //Vista de Contenido en forma de Cascada
        binding.rvTopNews.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initListeners(){
        binding.rfRecycler.setOnRefreshListener {
            initData()
            binding.rfRecycler.isRefreshing = false
        }

        binding.btnInsertar.setOnClickListener {
            addItem()
        }
    }

    private fun initData(){

        //Progress Indicators
        binding.prgbarData.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {
            val resultItem = GetAllNewsCase().invoke()

            withContext(Dispatchers.Main){

                binding.prgbarData.visibility = View.INVISIBLE

                resultItem.onSuccess {
                    items = it.toMutableList()
                    newsAdapter.listItem = items
                    newsAdapter.notifyDataSetChanged()
                }

                resultItem.onFailure { Snackbar.make(binding.rfRecycler, it.message.toString(), Snackbar.LENGTH_LONG).show() }
            }

        }
    }

    //Funciones Lambda
    private fun descriptionItem(news: NewsDataUI){

        Log.d("UUID", news.id)
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra("id", news.id)
            putExtra("image", news.image)
            putExtra("title", news.name)
            putExtra("url", news.url)
            putExtra("description", news.description)
        }
        startActivity(intent)
    }

    //Borrar noticia
    private fun deleteItem(position: Int){

        items.removeAt(position)
        newsAdapter.listItem = items
        newsAdapter.notifyItemRemoved(position)
        newsAdapter.notifyDataSetChanged()

    }

    //Agregar noticia
    private fun addItem(){

        items.add(NewsDataUI("1", "www.google.com","Noticia Fake", "123456", "123456"))
        newsAdapter.listItem = items
        newsAdapter.notifyItemInserted(items.size - 1)
        newsAdapter.notifyDataSetChanged()

    }

}