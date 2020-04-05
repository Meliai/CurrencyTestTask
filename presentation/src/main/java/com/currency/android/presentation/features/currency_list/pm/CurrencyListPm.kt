package com.currency.android.presentation.features.currency_list.pm

import com.currency.android.domain.features.currency.interactor.GetLatestCurrencyDataUseCase
import com.currency.android.domain.features.currency.model.CurrencyModel
import com.currency.android.presentation.Events
import com.currency.android.presentation.core.bus.events
import com.currency.android.presentation.core.pm.BaseListPm
import com.currency.android.presentation.core.pm.ServiceFacade
import com.currency.common.mapper.Mapper
import com.nullgr.core.adapter.items.ListItem
import io.reactivex.android.schedulers.AndroidSchedulers
import me.dmdev.rxpm.action
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrencyListPm @Inject constructor(
    private val getLatestCurrencyDataUseCase: GetLatestCurrencyDataUseCase,
    private val mapper: Mapper<CurrencyModel, ListItem>,
    services: ServiceFacade
) : BaseListPm(services) {

    private val loadScreenAction = action<Unit>()
    private var currentList: MutableList<CurrencyModel> = ArrayList()

    override fun onCreate() {
        super.onCreate()

        loadScreenAction.observable
            .flatMap {
                uploadData()
            }
            .subscribe()
            .untilDestroy()

        lifecycleObservable.filter { it == Lifecycle.CREATED }
            .doOnNext { loadScreenAction.consumer.accept(Unit) }
            .subscribe()
            .untilDestroy()
    }

    override fun onBind() {
        super.onBind()
        bus.events<Events.OnMultiplierChanged>()
            .doOnNext { event ->
                currentList.map {
                    it.multiplier = event.amount
                }
                items.consumer.accept(mapper.mapFromObjects(currentList))
            }
            .subscribe()
            .untilUnbind()
    }

    private fun uploadData() =
        getLatestCurrencyDataUseCase.execute(GetLatestCurrencyDataUseCase.Params(DEFAULT_CURRENCY))
            .doOnNext {
                currentList.add(
                    CurrencyModel(
                        currency = DEFAULT_CURRENCY,
                        isDefault = true
                    ))
                currentList.addAll(it)
                items.consumer.accept(mapper.mapFromObjects(currentList))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .repeatWhen { it.delay(FREQUENCY, TimeUnit.SECONDS) }
            .hideErrorContainer()

    private companion object {
        const val DEFAULT_CURRENCY = "EUR"
        const val FREQUENCY = 1L
    }
}