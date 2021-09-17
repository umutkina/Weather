package com.lemonz.weatherapp.data.model.search

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Region(
    val value: String
) : Parcelable