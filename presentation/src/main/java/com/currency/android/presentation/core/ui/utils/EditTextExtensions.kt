package com.currency.android.presentation.core.ui.utils

import android.text.method.PasswordTransformationMethod
import android.widget.EditText

fun EditText.toggleSecure(): Boolean {
    transformationMethod = when (transformationMethod == null) {
        true -> PasswordTransformationMethod.getInstance()
        else -> null
    }
    setSelection(text.length)
    return isSecure()
}

fun EditText.isSecure(): Boolean = transformationMethod != null