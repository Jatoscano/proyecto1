package com.toscano.proyecto1.ui.core

import com.toscano.proyecto1.data.network.entities.newsapi.allnews.Data
import com.toscano.proyecto1.ui.entities.NewsDataUI

class FunctionExtension
    fun Data.toNewsDataUI() = NewsDataUI(this.uuid, this.url, this.url, this.image_url, this.description)

