@file:Suppress("NOTHING_TO_INLINE", "UseDataClass")

package com.currency.android.presentation.core.pm.widgets

import com.currency.android.presentation.core.ui.state_view.StateData
import com.currency.android.presentation.core.ui.state_view.StateView
import io.reactivex.Observable
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.action
import me.dmdev.rxpm.bindTo
import me.dmdev.rxpm.state

/**
 * Class to observe state changes.
 */
class StateControl : PresentationModel() {
    val dataState = state<StateData>()
    val visibilityState = state<Boolean>()
    val stateAction = action<Unit>()
    val actionEnableState = state(true)

    var state: StateData?
        get() = dataState.value
        set(value) {
            dataState.consumer.accept(value)
        }

    var visibility: Boolean?
        get() = visibilityState.value
        set(value) {
            visibilityState.consumer.accept(value)
        }

    var enable: Boolean?
        get() = actionEnableState.value
        set(value) {
            actionEnableState.consumer.accept(value)
        }

    val observable: Observable<Unit>
        get() = stateAction.observable
}

fun PresentationModel.stateControl(): StateControl = StateControl().apply {
    attachToParent(this@stateControl)
}

fun StateControl.bindTo(view: StateView) {
    dataState.bindTo(view.state())
    visibilityState.bindTo(view.visibility())
    view.clicks().bindTo(stateAction)
    actionEnableState.bindTo(view.enable())
}