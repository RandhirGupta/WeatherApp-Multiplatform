package com.cyborg.weatherapp_multiplatform.common.utils

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.format
import com.soywiz.klock.parse

fun getDayName(inputDate: Long): String {
    val inFormat = DateFormat("yyyy-MM-dd")
    val parseInputDate = inFormat.format(DateTime.fromUnix(inputDate))
    val date = inFormat.parse(parseInputDate)
    val outFormat = DateFormat("EEEE")
    return outFormat.format(date)
}