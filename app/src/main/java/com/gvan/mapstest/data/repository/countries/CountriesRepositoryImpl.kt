package com.gvan.mapstest.data.repository.countries

import com.gvan.mapstest.data.api.CountriesService
import com.gvan.mapstest.data.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

//more about flow:
//https://proandroiddev.com/kotlin-flow-on-android-quick-guide-76667e872166

class CountriesRepositoryImpl(private val countriesService: CountriesService) : CountriesRepository {

    override fun getCountries(): Flow<List<Country>> {
        return flow {
            val countries = countriesService.getCountries()
            emit(countries)
        }.flowOn(Dispatchers.IO)
    }
}