package com.lemonz.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lemonz.weatherapp.R
import com.lemonz.weatherapp.common.AppPreferences
import com.lemonz.weatherapp.data.model.search.Place
import com.lemonz.weatherapp.databinding.CityListItemBinding
import com.lemonz.weatherapp.ui.WeatherFragment

class CityListAdapter(var placeList: List<Place> = arrayListOf(), val removeItem: (Place) -> Any) :
    RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {
    private lateinit var currentPlace: Place

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CityListItemBinding.inflate(inflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(placeList[position])
    }

    inner class CityViewHolder(rowBinding: CityListItemBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {
        private val binding = rowBinding
        init {
            currentPlace = AppPreferences.getCurrentCity()
            binding.root.setOnClickListener { view ->
                AppPreferences.firstRun = false
                AppPreferences.putCity(placeList[adapterPosition])
                val bundle = bundleOf(WeatherFragment.WEATHER_ARG to placeList[adapterPosition])
                val options = NavOptions.Builder().setPopUpTo(R.id.city_list_dest, true)
                    .setPopUpTo(R.id.weather_dest, true).build()
                view.findNavController().navigate(R.id.action_list_to_weather, bundle, options)
            }

            binding.ivDeleteCity.setOnClickListener {
                removeItem(placeList[adapterPosition])
            }
        }

        fun bind(place: Place) {
            binding.place = place
            if (place.id == currentPlace.id) {
                binding.ivDeleteCity.visibility = View.GONE
            }
            binding.executePendingBindings()
            binding.tvCity.text = "${place.areaName[0].value} "
        }
    }

}