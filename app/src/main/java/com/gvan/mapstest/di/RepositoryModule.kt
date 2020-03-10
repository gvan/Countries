package com.gvan.mapstest.di

import com.gvan.mapstest.data.api.CountriesService
import com.gvan.mapstest.data.repository.countries.CountriesRepository
import com.gvan.mapstest.data.repository.countries.CountriesRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCountriesRepository(countriesService: CountriesService) : CountriesRepository {
        return CountriesRepositoryImpl(countriesService)
    }

}