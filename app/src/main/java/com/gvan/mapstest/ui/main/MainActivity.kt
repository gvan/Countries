package com.gvan.mapstest.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gvan.mapstest.R
import com.gvan.mapstest.data.model.Country
import com.gvan.mapstest.databinding.ActivityMainBinding
import com.gvan.mapstest.ui.base.BaseActivity
import com.gvan.mapstest.ui.main.list.CountriesAdapter
import com.gvan.mapstest.ui.main.view_model.MainViewModel
import com.gvan.mapstest.ui.map.MapActivity
import com.gvan.mapstest.utils.Const

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    lateinit var adapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCountries()
    }

    override fun provideBinding(): Int {
        return R.layout.activity_main
    }

    override fun provideViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun setupUI() {
        binding.recycler.layoutManager = LinearLayoutManager(this)
        adapter = CountriesAdapter(onCountryClicked)
        binding.recycler.adapter = adapter
    }

    override fun setupViewModel() {
        viewModel.countriesLiveData.observe(this, Observer { data ->
            adapter.submitList(data.countries)

            binding.emptyView.visibility = if (data.countries.isEmpty())
                View.VISIBLE else View.GONE

            if(data.search) {
                //workaround to scroll to the top when user enters search word
                adapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
                    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                        super.onItemRangeRemoved(positionStart, itemCount)
                        binding.recycler.scrollToPosition(0)
                    }

                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        super.onItemRangeInserted(positionStart, itemCount)
                        binding.recycler.scrollToPosition(0)
                    }
                })
            } else {
                adapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
                })
            }

        })

        viewModel.openCountryLiveData.observe(this, Observer { country ->
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra(Const.COUNTRY_NAME, country.name)
            intent.putExtra(Const.LAT, country.latlng[0])
            intent.putExtra(Const.LNG, country.latlng[1])
            startActivity(intent)
        })

        viewModel.showProgressLiveData.observe(this, Observer { show ->
            binding.progress.visibility = if (show) View.VISIBLE else View.GONE
        })

    }

    private val onCountryClicked: (Country) -> Unit = { country ->
        viewModel.onCountryClicked(country)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object: OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(searchView.windowToken, 0)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onFilterChanged(newText)
                return true
            }
        })

        return true
    }
}
