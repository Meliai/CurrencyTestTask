package com.currency.android.presentation.core.pm.widgets

import android.view.View
import com.jakewharton.rxbinding3.view.visibility
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.bindTo
import me.dmdev.rxpm.state

open class ViewControl(
    initialVisible: Boolean,
    initialEnable: Boolean
) : PresentationModel() {
    val visibilityState = state(initialVisible)
    val enableState = state(initialEnable)

    var visibility: Boolean?
        get() = visibilityState.value
        set(value) {
            visibilityState.consumer.accept(value)
        }

    var enable: Boolean?
        get() = enableState.value
        set(value) {
            enableState.consumer.accept(value)
        }
}

fun PresentationModel.viewControl(
    initialVisible: Boolean = true,
    initialEnable: Boolean = true
): ViewControl = ViewControl(initialVisible, initialEnable).apply {
    attachToParent(this@viewControl)
}

fun ViewControl.bindTo(view: View) {
    visibilityState.bindTo(view.visibility())
    enableState.bindTo(view::setEnabled)
}

