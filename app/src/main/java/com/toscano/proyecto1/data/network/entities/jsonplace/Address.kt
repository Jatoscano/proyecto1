package com.toscano.proyecto1.data.network.entities.jsonplace

data class Address(
    val city: String?,
    val geo: Geo?,
    val street: String?,
    val suite: String?,
    val zipcode: String?
)