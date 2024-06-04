package com.toscano.proyecto1.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.toscano.proyecto1.R
import com.toscano.proyecto1.data.network.entities.newsapi.allnews.Data
import com.toscano.proyecto1.databinding.ItemTopNewsBinding
import com.toscano.proyecto1.ui.entities.NewsDataUI

/*
class NewsAdapter(private val listItem: List<Data>, private val onClickAction: (Data) -> Unit): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    //Clase que le da forma al RecyclerView
    //Hacemos una inyeccion de dependencias por medio de constructores
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view){

        //Enviamos una vista
        private val binding = ItemTopNewsBinding.bind(view)

        fun render(data: Data, onClickAction: (Data) -> Unit){

            //Implementacion con Glide
            //Glide.with(binding.root).load(data.image_url).into(binding.imgNews)
            //Implementacion con Coil
            binding.imgNews.load(data.image_url){
                placeholder(R.drawable.katana)
            }
            binding.txtTitleNews.text = data.title.toString()
            binding.txtUrlNews.text = data.url.toString()
            binding.txtDescpNews.text = data.description.toString()

            binding.btnBorrar.setOnClickListener{
                onClickAction(data)
            }

            //ItemView - Donde el layout se interactua
            itemView.setOnClickListener { onClickAction(data) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return NewsViewHolder(inflater.inflate(R.layout.item_top_news, parent,false))
    }

    override fun getItemCount() = listItem.size


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.render(listItem[position], onClickAction)
    }

}
 */



class NewsAdapter(private val onClickAction: (NewsDataUI) -> Unit, private val onDeleteItem: (Int) -> Unit, private val onAddItem: () -> Unit): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    //Clase que le da forma al RecyclerView
    //Hacemos una inyeccion de dependencias por medio de constructores
    var listItem: List<NewsDataUI> = emptyList()
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view){

        //Enviamos una vista
        private val binding = ItemTopNewsBinding.bind(view)

        fun render(data: NewsDataUI, onClickAction: (NewsDataUI) -> Unit, onDeleteItem: (Int) -> Unit, onAddItem: () -> Unit){

            //Implementacion con Glide
            //Glide.with(binding.root).load(data.image_url).into(binding.imgNews)
            //Implementacion con Coil
            binding.imgNews.load(data.image){
                placeholder(R.drawable.katana)
            }
            binding.txtTitleNews.text = data.name
            binding.txtUrlNews.text = data.url
            binding.txtDescpNews.text = data.description

            binding.btnBorrar.setOnClickListener{
                onDeleteItem(adapterPosition)
            }

            binding.btnInsertar.setOnClickListener {
                onAddItem()
            }

            //ItemView - Donde el layout se interactua
            itemView.setOnClickListener { onClickAction(data) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return NewsViewHolder(inflater.inflate(R.layout.item_top_news, parent,false))
    }

    override fun getItemCount() = listItem.size


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.render(listItem[position], onClickAction, onDeleteItem, onAddItem)
    }

}
