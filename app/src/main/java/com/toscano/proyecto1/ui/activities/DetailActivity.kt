package com.toscano.proyecto1.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.toscano.proyecto1.R
import com.toscano.proyecto1.databinding.ActivityDetailBinding
import com.toscano.proyecto1.ui.entities.NewsDataUI

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