package com.gvan.mapstest.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gvan.mapstest.data.model.Country
import com.gvan.mapstest.databinding.ItemCountryBinding
import com.gvan.mapstest.ui.main.list.view_holder.CountriesViewHolder

class CountriesAdapter constructor(private val listener: (Country) -> Unit) : ListAdapter<Country, CountriesViewHolder>(CountriesDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return CountriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class CountriesDiffUtil : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }

}