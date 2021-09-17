package com.lemonz.weatherapp.data.model.weather

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherIconUrl(
    val value: String
) : Parcelable