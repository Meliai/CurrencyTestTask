package com.currency.android.presentation.features.currency_list.pm

import com.currency.android.domain.features.currency.interactor.GetLatestCurrencyDataUseCase
import com.currency.android.domain.features.currency.model.CurrencyModel
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

    private fun uploadData() =
        getLatestCurrencyDataUseCase.execute(GetLatestCurrencyDataUseCase.Params("EUR"))
            .doOnSuccess {
                items.consumer.accept(mapper.mapFromObjects(it))
            }
            .hideErrorContainer()
}