package com.skoukio.movierama.common.extensions

import android.annotation.SuppressLint
import com.skoukio.movierama.common.DefinitionsApi.GLOBAL_PATTERN_FROM
import com.skoukio.movierama.common.DefinitionsApi.GLOBAL_PATTERN_TO
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.getFormattedDate(
    inputPattern: String = GLOBAL_PATTERN_FROM,
    outputPattern: String = GLOBAL_PATTERN_TO
): String {
    return try {
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    } catch (e: Exception) {
        ""
    }
}