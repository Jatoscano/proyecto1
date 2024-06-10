package com.toscano.proyecto1.data.network.entities.marvel

data class Comics(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)