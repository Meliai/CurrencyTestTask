package com.currency.android.presentation.features.currency_list.pm

import com.currency.android.domain.features.currency.interactor.GetLatestCurrencyDataUseCase
import com.currency.android.domain.features.currency.model.CurrencyModel
import com.currency.android.presentation.Events
import com.currency.android.presentation.core.bus.events
import com.currency.android.presentation.core.pm.BaseListPm
import com.currency.android.presentation.core.pm.ServiceFacade
import com.currency.common.mapper.Mapper
import com.nullgr.core.adapter.items.ListItem
import me.dmdev.rxpm.action
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
            .flatMapSingle { uploadData() }
            .retry()
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
            .doOnSuccess {
                currentList.add(
                    CurrencyModel(
                        DEFAULT_CURRENCY,
                        DEFAULT_RATE,
                        true
                    ))
                currentList.addAll(it)
                items.consumer.accept(mapper.mapFromObjects(currentList))
            }
            .hideErrorContainer()

    private companion object {
        const val DEFAULT_CURRENCY = "EUR"
        const val DEFAULT_RATE = 1.0
    }
}