package com.lemonz.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lemonz.weatherapp.common.AppPreferences
import com.lemonz.weatherapp.data.model.search.Place
import com.lemonz.weatherapp.data.utils.Result

class WeatherCityListViewModel() : ViewModel() {
    val places = MutableLiveData<Result<List<Place>>>()

    fun getCities() {
        val citymap = AppPreferences.getCurrentCityListForPrefs();
        citymap?.cityList?.remove(citymap.selectedPlace.id)
        val cityList = ArrayList(citymap?.cityList?.values)
        cityList.add(citymap?.selectedPlace)
        cityList.reverse()
        places.postValue(Result.Success(cityList))

    }

    fun removeCity(place: Place) {
        val citymap = AppPreferences.getRemovePlace(place);
        val cityList = ArrayList(citymap?.cityList?.values)
        places.postValue(Result.Success(cityList))

    }


}