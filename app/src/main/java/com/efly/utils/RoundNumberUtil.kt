package com.efly.utils

import java.text.NumberFormat
import java.util.Locale

/**
 * Created by vezikon on 4/8/17.
 */

object RoundNumberUtil {

    fun round(number: Double): Double {
        return Math.round(number * 1000) / 1000.0
    }

    fun formatNumber(number: Double): String {
        val numberFormat = NumberFormat.getInstance(Locale.ENGLISH)
        numberFormat.maximumFractionDigits = 3
        numberFormat.minimumFractionDigits = 3
        return numberFormat.format(round(number))
    }
}
