package com.lemonz.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemonz.weatherapp.data.model.weather.Data
import com.lemonz.weatherapp.data.repository.WeatherRepository
import com.lemonz.weatherapp.data.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    val weatherData = MutableLiveData<Result<Data>>()

    fun weather(query: String) {
        try {

            weatherData.postValue(Result.InProgress)
            viewModelScope.launch(Dispatchers.IO) {
                val response = weatherRepository.weather(query)
                response?.let {
                    var search = it.body()
                    search?.let { weatherResult ->
                        weatherData.postValue(Result.Success(weatherResult.data))
                    } ?: run {
                        weatherData.postValue(Result.Success(Data()))
                    }
                } ?: run {
                    weatherData.postValue(Result.Success(Data()))
                }
            }
        } catch (error: Exception) {
            weatherData.postValue(Result.Error(error.toString()))
        }


    }


}