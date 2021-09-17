package com.lemonz.weatherapp.data.model.weather

import android.os.Parcelable
import com.solidict.momsco.common.util.DateTimeUtil
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Weather(
    val astronomy: List<Astronomy>,
    val avgtempC: String,
    val avgtempF: String,
    val date: String,
    val hourly: List<Hourly>,
    val maxtempC: String,
    val maxtempF: String,
    val mintempC: String,
    val mintempF: String,
    val sunHour: String,
    val totalSnow_cm: String,
    val uvIndex: String
) : Parcelable {
    val weekDay: String
        get() = DateTimeUtil.convertToDay(date)
}
