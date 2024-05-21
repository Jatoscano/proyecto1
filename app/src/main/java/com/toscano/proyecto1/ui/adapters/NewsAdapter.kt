package com.toscano.proyecto1.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toscano.proyecto1.R
import com.toscano.proyecto1.data.network.entities.newsapi.topnews.Data
import com.toscano.proyecto1.databinding.ItemTopNewsBinding

class NewsAdapter(private val listItem: List<Data>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    //Clase que le da forma al RecyclerView
    //Hacemos una inyeccion de dependencias por medio de constructores
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view){

        //Enviamos una vista
        private val binding = ItemTopNewsBinding.bind(view)

        fun render(data: Data){

            binding.txtTitleNews.text = data.title.toString()
            binding.txtUrlNews.text = data.url.toString()
            binding.txtDescpNews.text = data.description.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return NewsViewHolder(inflater.inflate(R.layout.item_top_news,parent,false))
    }

    override fun getItemCount() = listItem.size


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.render(listItem[position])
    }

}