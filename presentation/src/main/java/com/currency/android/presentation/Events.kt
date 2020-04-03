package com.currency.android.presentation

import com.currency.android.presentation.core.bus.Event

sealed class Events : Event {
    data class OnMultiplierChanged(val amount: String) : Events()
}