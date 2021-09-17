package com.lemonz.weatherapp.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.lemonz.weatherapp.data.model.search.Place
import com.lemonz.weatherapp.data.prefs.CityListForPrefs

object AppPreferences {
    private const val NAME = "WEATHER_APP"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences
    private val IS_FIRST_RUN_PREF = Pair("is_first_run", true)
    private const val CITY_LIST = "CITY_LIST"


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firstRun: Boolean
        get() = preferences.getBoolean(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_FIRST_RUN_PREF.first, value)
        }


    fun putCity(place: Place) {
        val cityList: LinkedHashMap<String, Place>
        var cityListForPrefs = getCurrentCityListForPrefs()
        if (cityListForPrefs == null) {
            cityList = LinkedHashMap<String, Place>()
            cityList.put(place.id, place)
            cityListForPrefs = CityListForPrefs(cityList, place)
        } else {
            cityList = cityListForPrefs.cityList
            cityList.put(place.id, place)
            cityListForPrefs = CityListForPrefs(cityList, place)
        }
        val jsonString = GsonBuilder().create().toJson(cityListForPrefs)
        preferences.edit().putString(CITY_LIST, jsonString).apply()
    }

    fun getCurrentCity(): Place {
        return getCurrentCityListForPrefs()?.selectedPlace!!
    }

    fun getCurrentCityListForPrefs(): CityListForPrefs? {
        val value = preferences.getString(CITY_LIST, null)
        return GsonBuilder().create().fromJson(value, CityListForPrefs::class.java)
    }

    fun getRemovePlace(place: Place): CityListForPrefs? {
        val value = preferences.getString(CITY_LIST, null)
        val oldCityList = GsonBuilder().create().fromJson(value, CityListForPrefs::class.java);
        oldCityList.cityList.remove(place.id)
        val newCityList = CityListForPrefs(oldCityList.cityList, getCurrentCity()!!)
        val jsonString = GsonBuilder().create().toJson(newCityList)
        preferences.edit().putString(CITY_LIST, jsonString).apply()
        return newCityList
    }
}