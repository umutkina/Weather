package com.lemonz.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.lemonz.weatherapp.data.model.weather.Weather
import com.lemonz.weatherapp.databinding.DailyWeatherAdapterBinding

class DailyWeatherAdapter(var weatherList: List<Weather> = arrayListOf()) :
    RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DailyWeatherAdapterBinding.inflate(inflater, parent, false)
        return DailyWeatherHolder(binding)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: DailyWeatherHolder, position: Int) {
        holder.bind(weatherList[position])

    }

    inner class DailyWeatherHolder(rowBinding: DailyWeatherAdapterBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {
        private val binding = rowBinding

        fun bind(weather: Weather) {
            binding.weather = weather
            binding.executePendingBindings()

        }
    }

}