package com.lemonz.weatherapp.data.repository

import android.util.Log
import com.lemonz.weatherapp.data.model.search.Search
import com.lemonz.weatherapp.data.model.weather.Api
import com.umutkina.breakingbadcharacters.data.api.ApiService
import retrofit2.Response

class WeatherRepository(private val apiService: ApiService) {

    private val TAG = WeatherRepository::class.java.name


    suspend fun search(query: String): Response<Search>? {

        if (query.isEmpty()) {
            return null
        } else {
            try {
                val response = apiService.search(query)
                response?.let {
                    return it
                }
            } catch (error: Exception) {
                Log.e(TAG, "Error: ${error.message}")
                return null
            }
        }
        return null
    }

    suspend fun weather(query: String): Response<Api>? {


        try {
            val response = apiService.weather(query)
            response?.let {
                return it
            }
        } catch (error: Exception) {
            Log.e(TAG, "Error: ${error.message}")
            return null
        }

        return null
    }


}