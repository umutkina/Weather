package com.lemonz.weatherapp.common


import android.app.Activity
import android.app.Application
import android.content.Context

fun Context?.isDoomed(): Boolean = when (this) {
    null -> true
    is Application -> false
    is Activity -> (this.isDestroyed or this.isFinishing)
    else -> false
}
