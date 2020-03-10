package com.gvan.mapstest.ui.map.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gvan.mapstest.ui.map.model.Point
import javax.inject.Inject

class MapViewModel @Inject constructor() : ViewModel() {

    lateinit var countryName: String
    lateinit var center: Point

    val countryNameLiveData = MutableLiveData<String>()
    val countryPointLiveData = MutableLiveData<Point>()

    fun init(){
        countryNameLiveData.value = countryName
    }

    fun onMapReady() {
        countryPointLiveData.value = center
    }

}