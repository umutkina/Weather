package com.lemonz.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lemonz.weatherapp.R
import com.lemonz.weatherapp.common.AppPreferences
import com.lemonz.weatherapp.data.model.search.Place
import com.lemonz.weatherapp.databinding.CitySearchItemBinding
import com.lemonz.weatherapp.ui.WeatherFragment

class CitySearchAdapter(var placeList: List<Place> = arrayListOf()) :
    RecyclerView.Adapter<CitySearchAdapter.CitySearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitySearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CitySearchItemBinding.inflate(inflater, parent, false)
        return CitySearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: CitySearchViewHolder, position: Int) {
        holder.bind(placeList[position])
    }

    inner class CitySearchViewHolder(rowBinding: CitySearchItemBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {
        private val binding = rowBinding
        init {
            binding.root.setOnClickListener { view ->
                AppPreferences.firstRun = false
                AppPreferences.putCity(placeList[adapterPosition])
                val bundle = bundleOf(WeatherFragment.WEATHER_ARG to placeList[adapterPosition])
                val options = NavOptions.Builder().setPopUpTo(R.id.list_search_dest, true)
                    .setPopUpTo(R.id.weather_dest, true).build()
                view.findNavController().navigate(R.id.action_search_to_weather, bundle, options)
            }
        }

        fun bind(place: Place) {
            binding.place = place
            binding.executePendingBindings()

        }
    }

}