package com.gvan.mapstest.ui.map

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gvan.mapstest.R
import com.gvan.mapstest.databinding.ActivityMapBinding
import com.gvan.mapstest.ui.base.BaseActivity
import com.gvan.mapstest.ui.map.model.Point
import com.gvan.mapstest.ui.map.view_model.MapViewModel
import com.gvan.mapstest.utils.Const

class MapActivity : BaseActivity<ActivityMapBinding, MapViewModel>(), OnMapReadyCallback {

    var map: GoogleMap? = null

    override fun provideBinding(): Int {
        return R.layout.activity_map
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
        viewModel.init()
    }

    override fun provideViewModel(): Class<MapViewModel> {
        return MapViewModel::class.java
    }

    fun parseArguments() {
        if (intent.extras != null && intent.extras!!.containsKey(Const.COUNTRY_NAME)) {
            val countryName = intent.extras!!.getString(Const.COUNTRY_NAME, "")
            val lat = intent.extras!!.getDouble(Const.LAT)
            val lng = intent.extras!!.getDouble(Const.LNG)

            viewModel.countryName = countryName
            viewModel.center = Point(lat, lng)
        }
    }

    override fun setupUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun setupViewModel() {
        viewModel.countryNameLiveData.observe(this, Observer { name ->
            supportActionBar?.title = name
        })

        viewModel.countryPointLiveData.observe(this, Observer { p ->
            if(map != null) {
                val p1 = LatLng(p.lat, p.lng)
                map!!.moveCamera(CameraUpdateFactory.newLatLngZoom(p1, 5.0f))
                map!!.addMarker(MarkerOptions().position(p1))
            }
        })

    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map
        viewModel.onMapReady()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}