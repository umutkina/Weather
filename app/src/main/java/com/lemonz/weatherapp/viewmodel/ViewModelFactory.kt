package com.lemonz.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lemonz.weatherapp.data.repository.WeatherRepository
import com.umutkina.breakingbadcharacters.data.api.ApiService
import com.umutkina.breakingbadcharacters.data.api.RetrofitService

class ViewModelFactory(private val apiService: ApiService = RetrofitService.getInstance()) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherSearchViewModel::class.java)) {
            return WeatherSearchViewModel(
                WeatherRepository(apiService)
            ) as T
        }
        else if(modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(
                WeatherRepository(apiService)
            ) as T
        }

        else if(modelClass.isAssignableFrom(WeatherCityListViewModel::class.java)) {
            return WeatherCityListViewModel() as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}