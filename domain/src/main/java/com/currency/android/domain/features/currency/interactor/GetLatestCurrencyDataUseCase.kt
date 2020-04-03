package com.currency.android.domain.features.currency.interactor

import com.currency.android.domain.features.currency.model.CurrencyModel
import com.currency.android.domain.features.currency.repository.CurrencyRepository
import com.nullgr.core.interactor.SingleUseCase
import com.nullgr.core.rx.schedulers.SchedulersFacade
import io.reactivex.Single
import javax.inject.Inject

class GetLatestCurrencyDataUseCase @Inject constructor(
    private val repository: CurrencyRepository,
    schedulersFacade: SchedulersFacade
) : SingleUseCase<List<CurrencyModel>, GetLatestCurrencyDataUseCase.Params>(schedulersFacade) {

    override fun buildUseCaseObservable(params: Params?): Single<List<CurrencyModel>> {
        val p = checkNotNull(params)
        return repository.getLatestCurrencyData(p.baseCurrency)
    }

    data class Params(val baseCurrency: String)
}