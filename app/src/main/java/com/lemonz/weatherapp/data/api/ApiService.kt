package com.umutkina.breakingbadcharacters.data.api

import com.lemonz.weatherapp.BuildConfig
import com.lemonz.weatherapp.data.model.search.Search
import com.lemonz.weatherapp.data.model.weather.Api
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.ashx")
    suspend fun search(
        @Query("q") query: String,
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("format") format: String = "json",
        @Query("popular") popular: String = "yes"
    ): Response<Search>?

    @GET("weather.ashx")
    suspend fun weather(
        @Query("q") query: String,
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("format") format: String = "json",
        @Query("num_of_days") popular: Int = 6
    ): Response<Api>?

}