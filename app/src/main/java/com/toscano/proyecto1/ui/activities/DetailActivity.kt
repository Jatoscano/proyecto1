package com.toscano.proyecto1.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.toscano.proyecto1.data.network.entities.onenews.OneNewsData
import com.toscano.proyecto1.databinding.ActivityDetailBinding
import com.toscano.proyecto1.logic.news.GetOneNewsCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var itemId: String
    private lateinit var itemTitle: String
    private lateinit var itemUrl: String
    private lateinit var itemImage: String
    private lateinit var itemDescription: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras.let {
            itemId = it?.getString("id").toString()
            itemUrl = it?.getString("url").toString()
            itemTitle = it?.getString("title").toString()
            itemImage = it?.getString("image").toString()
            itemDescription = it?.getString("description").toString()
        }

        binding.txtId.text = itemId
        binding.txtUrl.text = itemUrl
        binding.txtTitle.text = itemTitle
        Glide.with(binding.root).load(itemImage).into(binding.txtImage)
        binding.txtDescription.text = itemDescription

    }
}
 */

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var itemId: String
    private lateinit var itemTitle: String
    private lateinit var itemUrl: String
    private lateinit var itemImage: String
    private lateinit var itemDescription: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras.let {
            itemId = it?.getString("id").toString()
            itemUrl = it?.getString("url").toString()
            itemTitle = it?.getString("title").toString()
            itemImage = it?.getString("image").toString()
            itemDescription = it?.getString("description").toString()
        }

        lifecycleScope.launch(Dispatchers.Main){
            val item = withContext(Dispatchers.IO){
                getData()
            }

            if (item != null){
                binding.txtId.text = itemId
                binding.txtUrl.text = itemUrl
                binding.txtTitle.text = itemTitle
                Glide.with(binding.root).load(itemImage).into(binding.txtImage)
                binding.txtDescription.text = itemDescription
            }
        }

    }

    suspend fun getData(): Result<OneNewsData?>{

        return GetOneNewsCase().invoke(itemId)
             .onSuccess { it }
             .onFailure { Snackbar.make(binding.txtId, it.message.toString(), Snackbar.LENGTH_LONG).show() }
    }
}