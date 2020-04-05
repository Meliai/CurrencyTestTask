package com.currency.android.presentation.features.currency_list

import com.currency.android.presentation.R

fun String.chooseDrawable(): Int? =
    when (this) {
        CurrencyEnum.CANADA.code -> R.drawable.ic_flag_canada
        CurrencyEnum.SWEDEN.code -> R.drawable.ic_flag_sweden
        CurrencyEnum.USA.code -> R.drawable.ic_flag_usa
        CurrencyEnum.AUSTRALIA.code -> R.drawable.ic_flag_australia
        CurrencyEnum.POLAND.code -> R.drawable.ic_flag_poland
        CurrencyEnum.BULGARIA.code -> R.drawable.ic_flag_bulgaria
        CurrencyEnum.PHILIPPINES.code -> R.drawable.ic_flag_philippines
        CurrencyEnum.EU.code -> R.drawable.ic_flag_eu
        CurrencyEnum.CROATIA.code -> R.drawable.ic_flag_croatia
        else -> null
    }

enum class CurrencyEnum(val code: String) {
    CANADA("CAD"),
    SWEDEN("SEK"),
    USA("USD"),
    AUSTRALIA("AUD"),
    POLAND("PLN"),
    BULGARIA("BGN"),
    PHILIPPINES("PHP"),
    EU("EUR"),
    CROATIA("HRK")
}