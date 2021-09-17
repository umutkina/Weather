package com.lemonz.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemonz.weatherapp.data.model.search.Place
import com.lemonz.weatherapp.data.repository.WeatherRepository
import com.lemonz.weatherapp.data.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherSearchViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    val places = MutableLiveData<Result<List<Place>>>()

    fun search(query: String = "") {


        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = weatherRepository.search(query)
                response?.let {
                    var search = it.body()?.search_api
                    search?.let { searchResult ->
                        places.postValue(Result.Success(searchResult.result))
                    } ?: run {
                        places.postValue(Result.Error(it.body()?.data?.error?.get(0)?.msg))
                    }
                } ?: run {
                    places.postValue(Result.Success(arrayListOf()))
                }
            }

        } catch (error: Exception) {
            places.postValue(Result.Error(error.toString()))
        }


    }


}