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
    private val builder: Mapper<CurrencyModel, ListItem>,
    services: ServiceFacade
) : BaseListPm(services) {

    private val loadScreenAction = action<Unit>()
    private var currentList: MutableList<CurrencyModel> = ArrayList()
    private var multiplier = DEFAULT_MULTIPLIER
    private var currency = DEFAULT_CURRENCY

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
                multiplier = event.amount
                currentList.map {
                    it.multiplier = multiplier
                }
                items.consumer.accept(builder.mapFromObjects(currentList))
            }
            .subscribe()
            .untilUnbind()
    }

    private fun uploadData() =
        getLatestCurrencyDataUseCase.execute(GetLatestCurrencyDataUseCase.Params(DEFAULT_CURRENCY))
            .doOnNext { list ->
                currentList.clear()
                currentList.add(
                    CurrencyModel(
                        currency = currency,
                        multiplier = multiplier,
                        isDefault = true
                    ))
                list.map {
                    currentList.add(
                        CurrencyModel(
                            currency = it.currency,
                            multiplier = multiplier,
                            rate = it.rate
                        ))
                }
                items.consumer.accept(builder.mapFromObjects(currentList))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .repeatWhen { it.delay(FREQUENCY, TimeUnit.SECONDS) }
            .hideErrorContainer()

    companion object {
        const val DEFAULT_CURRENCY = "EUR"
        const val DEFAULT_MULTIPLIER = 1.0
        const val FREQUENCY = 1L
    }
}