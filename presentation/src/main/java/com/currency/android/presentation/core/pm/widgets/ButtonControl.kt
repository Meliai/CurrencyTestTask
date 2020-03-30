package com.currency.android.presentation.core.pm.widgets

import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.visibility
import io.reactivex.Observable
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.action
import me.dmdev.rxpm.bindTo

class ButtonControl(
    initialVisible: Boolean,
    initialEnable: Boolean
) : ViewControl(initialVisible, initialEnable) {
    val clickAction = action<Unit>()

    val observable: Observable<Unit>
        get() = clickAction.observable
}

fun PresentationModel.buttonControl(
    initialVisible: Boolean = true,
    initialEnable: Boolean = true
): ButtonControl = ButtonControl(initialVisible, initialEnable).apply {
    attachToParent(this@buttonControl)
}

fun ButtonControl.bindTo(view: View) {
    visibilityState.bindTo(view.visibility())
    enableState.bindTo(view::setEnabled)
    view.clicks().bindTo(clickAction)
}