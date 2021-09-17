package com.lemonz.weatherapp.data.model.weather

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClimateAverage(
    val month: List<Month>
) : Parcelable