package com.lemonz.weatherapp.data.model.weather

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val ClimateAverages: List<ClimateAverage> = arrayListOf(),
    val current_condition: List<CurrentCondition> = arrayListOf(),
    val request: List<Request> = arrayListOf(),
    val weather: List<Weather> = arrayListOf()
) : Parcelable