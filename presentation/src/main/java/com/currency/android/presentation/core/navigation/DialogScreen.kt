package com.currency.android.presentation.core.navigation

import androidx.fragment.app.DialogFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

abstract class DialogScreen : SupportAppScreen() {
    abstract override fun getFragment(): DialogFragment
}