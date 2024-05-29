package com.toscano.proyecto1.ui.entities

import com.toscano.proyecto1.data.network.entities.newsapi.allnews.Data

data class NewsDataUI (var id: String,
                       var url: String,
                       var name: String,
                       var image: String,
                       var description: String)

fun NewsDataUI.toString(){
    println(this.name)
}

