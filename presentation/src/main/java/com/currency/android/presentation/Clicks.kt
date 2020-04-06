package com.currency.android.presentation

import com.currency.android.presentation.core.bus.Click

sealed class Clicks : Click {

    data class ChangeBaseCurrency(val currency: String) : Click
}