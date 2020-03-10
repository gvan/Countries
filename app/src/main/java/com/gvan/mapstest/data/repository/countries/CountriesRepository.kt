package com.gvan.mapstest.data.repository.countries

import com.gvan.mapstest.data.model.Country
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {

 fun getCountries(): Flow<List<Country>>

}