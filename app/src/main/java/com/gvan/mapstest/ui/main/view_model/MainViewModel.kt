package com.gvan.mapstest.ui.main.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gvan.mapstest.data.local_data.LocalDataManager
import com.gvan.mapstest.data.model.Country
import com.gvan.mapstest.data.repository.countries.CountriesRepository
import com.gvan.mapstest.ui.main.model.CountriesData
import com.gvan.mapstest.utils.SingleLiveEvent
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val countriesRepository: CountriesRepository,
                                        private val localDataManager: LocalDataManager) : ViewModel() {

    private val allCountries : MutableList<Country> = arrayListOf()
    private var filter: String = ""

    val countriesLiveData = MutableLiveData<CountriesData>()
    val openCountryLiveData = SingleLiveEvent<Country>()
    val showProgressLiveData = SingleLiveEvent<Boolean>()

    fun getCountries() {
        showProgressLiveData.value = true
        viewModelScope.launch {
            countriesRepository.getCountries()
                .catch {
                    it.printStackTrace()
                    showProgressLiveData.value = false
                    countriesLiveData.value = CountriesData(arrayListOf(), false)
                }
                .collect { countries ->
                    showProgressLiveData.value = false
                    allCountries.clear()
                    allCountries.addAll(countries)
                    val resultCountries = applyFilter(allCountries, filter)
                    countriesLiveData.value = CountriesData(resultCountries, false)
                }
        }
    }

    fun onCountryClicked(country: Country){
        openCountryLiveData.value = country
    }

    private fun applyFilter(list:List<Country>, filter: String) : List<Country> {
        return list.filter { country -> country.name.toLowerCase().contains(filter) or
                country.capital.toLowerCase().contains(filter) or
                country.region.toLowerCase().contains(filter)}
    }

    fun onFilterChanged(filter: String?) {
        this.filter = (filter ?: "").toLowerCase()
        val resultCountries = applyFilter(allCountries, this.filter)
        countriesLiveData.value = CountriesData(resultCountries, true)
    }

}