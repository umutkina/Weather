package com.solidict.momsco.common.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateTimeUtil {


    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("EEEE")

    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("yyyy-MM-dd")

    fun convertToDay(d: String): String {
        if (d.isEmpty()) return ""
        var isoDate = ""
        formatter.parse(d)?.let {
            isoDate = sdf.format(it)
        }
        return isoDate
    }

}