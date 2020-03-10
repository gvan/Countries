package com.gvan.mapstest.data.model


data class Country constructor(
    val name: String,
    val capital: String,
    val region: String,
    val population: Int,
    val latlng: DoubleArray,
    val area: Double
) {
}