package com.currency.android.presentation.features.currency_list

import com.currency.android.presentation.R

fun String.chooseDrawable(): Int? =
    when (this) {
        CurrencyEnum.CANADA.code -> R.drawable.ic_cad_flag
        CurrencyEnum.SWEDEN.code -> R.drawable.ic_swd_flag
        CurrencyEnum.USA.code -> R.drawable.ic_usa_flag
        CurrencyEnum.AUSTRALIA.code -> R.drawable.ic_aus_flag
        CurrencyEnum.POLAND.code -> R.drawable.ic_poland_flag
        CurrencyEnum.BULGARIA.code -> R.drawable.ic_bulgaria_flag
        CurrencyEnum.PHILIPPINES.code -> R.drawable.ic_phili_flag
        CurrencyEnum.EU.code -> R.drawable.ic_eu_flag
        CurrencyEnum.CROATIA.code -> R.drawable.ic_croatia_flag
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