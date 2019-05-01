package com.flexsentlabs.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun BigDecimal.toTwoDigitAfterPointString(): String {

    val otherSymbols = DecimalFormatSymbols(Locale.getDefault())
    otherSymbols.decimalSeparator = '.'

    val numberFormat = DecimalFormat("0.00", otherSymbols)
    numberFormat.isGroupingUsed = true
    numberFormat.maximumFractionDigits = 2
    numberFormat.minimumFractionDigits = 2

    return numberFormat.format(this)
}