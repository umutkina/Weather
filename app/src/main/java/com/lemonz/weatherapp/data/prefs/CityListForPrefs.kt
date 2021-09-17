package com.lemonz.weatherapp.data.prefs

import com.lemonz.weatherapp.data.model.search.Place

data class CityListForPrefs(val cityList: LinkedHashMap<String, Place>, val selectedPlace: Place)
