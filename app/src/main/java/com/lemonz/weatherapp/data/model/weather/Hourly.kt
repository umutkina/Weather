package com.lemonz.weatherapp.data.model.weather

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Hourly(
    val DewPointC: String,
    val DewPointF: String,
    val FeelsLikeC: String,
    val FeelsLikeF: String,
    val HeatIndexC: String,
    val HeatIndexF: String,
    val WindChillC: String,
    val WindChillF: String,
    val WindGustKmph: String,
    val WindGustMiles: String,
    val chanceoffog: String,
    val chanceoffrost: String,
    val chanceofhightemp: String,
    val chanceofovercast: String,
    val chanceofrain: String,
    val chanceofremdry: String,
    val chanceofsnow: String,
    val chanceofsunshine: String,
    val chanceofthunder: String,
    val chanceofwindy: String,
    val cloudcover: String,
    val humidity: String,
    val precipInches: String,
    val precipMM: String,
    val pressure: String,
    val pressureInches: String,
    val tempC: String,
    val tempF: String,
    val time: String,
    val uvIndex: String,
    val visibility: String,
    val visibilityMiles: String,
    val weatherCode: String,
    val weatherDesc: List<WeatherDesc>,
    val weatherIconUrl: List<WeatherIconUrl>,
    val winddir16Point: String,
    val winddirDegree: String,
    val windspeedKmph: String,
    val windspeedMiles: String
) : Parcelable