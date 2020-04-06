package com.currency.android.presentation.core.pm.widgets

import com.currency.android.presentation.ErrorData
import com.currency.android.presentation.core.pm.BasePm
import com.currency.common.errors.NetworkConnectionError

class ErrorHandler(private val pm: BasePm) {

    fun handleError(error: Throwable) {
        val errorData = when (error) {
            is NetworkConnectionError -> ErrorData.NetworkErrorState(pm.resources)
            else -> ErrorData.ErrorState(pm.resources)
        }

        pm.setErrorStateData(errorData)
        if (pm.isEmptyScreen) {
            pm.setErrorViewVisibility(true)
        } else {
            pm.setErrorViewVisibility(false)
        }
    }
}

fun BasePm.errorHandler() = ErrorHandler(this)