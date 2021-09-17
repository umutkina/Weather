package com.lemonz.weatherapp.data.model.search

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(
    val areaName: List<AreaName>,
    val country: List<Country>,
    val latitude: String,
    val longitude: String,
    val population: String,
    val region: List<Region>,
    val weatherUrl: List<WeatherUrl>,
) : Parcelable {
    val id: String
        get() = "$latitude, $longitude"
}