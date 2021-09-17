package com.lemonz.weatherapp.data.model.weather

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Month(
    val absMaxTemp: String,
    val absMaxTemp_F: String,
    val avgDailyRainfall: String,
    val avgMinTemp: String,
    val avgMinTemp_F: String,
    val index: String,
    val name: String
) : Parcelable