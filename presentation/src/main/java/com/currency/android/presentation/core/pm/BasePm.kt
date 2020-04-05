@file:Suppress("NOTHING_TO_INLINE", "TooManyFunctions", "MethodOverloading", "SpreadOperator")

package com.currency.android.presentation.core.pm

import android.util.Log
import com.currency.android.presentation.core.navigation.FlowRouter
import com.currency.android.presentation.core.pm.listeners.ConnectionListener
import com.currency.android.presentation.core.pm.widgets.ErrorHandler
import com.currency.android.presentation.core.pm.widgets.errorHandler
import com.currency.android.presentation.core.pm.widgets.networkControl
import com.currency.android.presentation.core.pm.widgets.stateControl
import com.currency.android.presentation.core.ui.state_view.StateData
import com.nullgr.core.rx.bindProgress
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.action
import me.dmdev.rxpm.command
import me.dmdev.rxpm.skipWhileInProgress
import me.dmdev.rxpm.state
import java.util.concurrent.TimeUnit

/**
 * Base [PresentationModel] with bunch of useful methods and extensions.
 */
abstract class BasePm(
    protected val services: ServiceFacade
) : PresentationModel() {

    lateinit var router: FlowRouter

    val progressState = state(false)
    val progressDialogState = state(false)

    val hideKeyBoardCommand = command<Unit>()
    val showKeyBoardCommand = command<Unit>()

    val networkStateAction = action<Boolean>()
    val networkStateCommand = command<Boolean>(bufferSize = 1)

    val errorControl = stateControl()
    val emptyControl = stateControl()

    protected val resources = services.resources
    protected val network = services.network
    internal val bus = services.bus

    protected val errorHandler: ErrorHandler = errorHandler()

    private val networkControl by lazy { networkControl(network) }

    open val isEmptyScreen: Boolean = false

    override fun onCreate() {
        super.onCreate()

        if (this is ConnectionListener) {
            networkStateAction.observable
                .doOnNext { networkStateCommand.consumer.accept(it) }
                .subscribe()
                .untilDestroy()

            networkControl.observable
                .subscribe()
                .untilDestroy()
        }
    }

    internal fun setErrorStateData(data: StateData) {
        errorControl.state = data
    }

    internal fun setErrorViewVisibility(visible: Boolean) {
        errorControl.visibility = visible
    }

    protected open fun handleError(error: Throwable) {
        Log.e("BasePm handle error", error.message)
        errorHandler.handleError(error)
    }

    protected inline fun <T> Observable<T>.debounceAction(): Observable<T> =
        this.throttleFirst(ACTION_DEBOUNCE_MILLIS, TimeUnit.MILLISECONDS)

    protected inline fun <T> Observable<T>.hideErrorContainer(): Observable<T> =
        this.doOnSubscribe { errorControl.visibility = false }

    protected inline fun <T> Single<T>.hideErrorContainer(): Single<T> =
        this.doOnSubscribe { errorControl.visibility = false }

    protected inline fun Completable.hideErrorContainer(): Completable =
        this.doOnSubscribe { errorControl.visibility = false }

    protected inline fun <T> Observable<T>.skipWhileInProgress(): Observable<T> =
        this.skipWhileInProgress(progressState.observable)

    protected inline fun <T> Observable<T>.bindProgress(): Observable<T> =
        this.bindProgress(progressState.consumer)

    protected inline fun <T> Single<T>.bindProgress(): Single<T> =
        this.bindProgress(progressState.consumer)

    protected inline fun Completable.bindProgress(): Completable =
        this.bindProgress(progressState.consumer)

    companion object {
        const val ACTION_DEBOUNCE_MILLIS = 500L
        const val RELOAD_DELAY_MILLIS = 3000L
    }
}