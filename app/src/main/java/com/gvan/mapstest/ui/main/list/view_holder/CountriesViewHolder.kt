package com.gvan.mapstest.ui.main.list.view_holder

import androidx.recyclerview.widget.RecyclerView
import com.gvan.mapstest.R
import com.gvan.mapstest.data.model.Country
import com.gvan.mapstest.databinding.ItemCountryBinding
import java.text.DecimalFormat

class CountriesViewHolder (private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.getRoot()) {

    fun bind(country: Country, listener: (Country) -> Unit){
        binding.name.text = country.name
        binding.capital.text = country.capital
        binding.region.text = country.region

        binding.population.text = String.format("%,d", country.population)

        val area = DecimalFormat("###,##0.0").format(country.area)
        binding.area.text = binding.root.context.getString(R.string.ga, area.toString())

        binding.root.setOnClickListener { _ -> listener(country) }
    }

}