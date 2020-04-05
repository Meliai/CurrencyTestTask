package com.currency.android.presentation.core.pm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import com.currency.android.presentation.core.pm.factory.PmFactory
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.base.PmFragment
import javax.inject.Inject

abstract class BasePmFragment<T : PresentationModel> : PmFragment<T>() {

    @Inject
    lateinit var factory: PmFactory

    protected abstract val screenLayout: Int

    protected abstract val classToken: Class<T>

    protected val compositeUnbind = CompositeDisposable()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(screenLayout, container, false)

    override fun onSaveInstanceState(outState: Bundle) {
        compositeUnbind.clear()
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        compositeUnbind.clear()
        super.onStop()
    }

    override fun providePresentationModel(): T = factory.createViewModel(classToken)
}