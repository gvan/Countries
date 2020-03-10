package com.gvan.mapstest.data.api

import com.gvan.mapstest.data.model.Country
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesService {

    @GET("rest/v1/all")
    suspend fun getCountries() : List<Country>


}