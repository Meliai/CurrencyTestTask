package com.currency.android.presentation.features.currency_list.pm

import com.currency.android.presentation.core.pm.BasePm
import com.currency.android.presentation.core.pm.ServiceFacade
import javax.inject.Inject

class CurrencyListPm @Inject constructor(
    services: ServiceFacade
) : BasePm(services)