package com.currency.android.presentation.core.pm.widgets

import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.action
import me.dmdev.rxpm.state

class SnackBarControl<T> : PresentationModel() {

    val displayed = state<Display>(Display.Absent)
    private val result = action<Unit>()


    fun show(data: T) {
        dismiss()
        displayed.consumer.accept(Display.Displayed(data))
    }

    fun showForResult(data: T): Maybe<Unit> {

        dismiss()

        return result.observable
            .doOnSubscribe {
                displayed.consumer.accept(Display.Displayed(data))
            }
            .takeUntil(
                displayed.observable
                    .skip(1)
                    .filter { it == Display.Absent }
            )
            .firstElement()
    }

    fun sendResult() {
        this.result.consumer.accept(Unit)
        dismiss()
    }

    fun dismiss() {
        if (displayed.value is Display.Displayed<*>) {
            displayed.consumer.accept(Display.Absent)
        }
    }

    sealed class Display {
        data class Displayed<T>(val data: T) : Display()
        object Absent : Display()
    }
}

fun <T> PresentationModel.snackBarControl(): SnackBarControl<T> = SnackBarControl<T>().apply {
    attachToParent(this@snackBarControl)
}

internal inline fun <T> SnackBarControl<T>.bindTo(
    crossinline createSnackBar: (data: T, dc: SnackBarControl<T>) -> Snackbar
) {

    var snackbar: Snackbar? = null
    val callback: BaseTransientBottomBar.BaseCallback<Snackbar> =
        object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                dismiss()
            }
        }

    val closeSnackbar: () -> Unit = {
        snackbar?.removeCallback(callback)
        snackbar?.dismiss()
        snackbar = null
    }

    displayed.observable
        .observeOn(AndroidSchedulers.mainThread())
        .doFinally { closeSnackbar() }
        .subscribe {
            @Suppress("UNCHECKED_CAST")
            if (it is SnackBarControl.Display.Displayed<*>) {
                snackbar = createSnackBar(it.data as T, this)
                snackbar?.addCallback(callback)
                snackbar?.show()
            } else if (it === SnackBarControl.Display.Absent) {
                closeSnackbar()
            }
        }
        .untilUnbind()
}