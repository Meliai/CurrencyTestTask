package com.currency.android.presentation

import com.currency.android.presentation.core.ui.state_view.StateData
import com.nullgr.core.resources.ResourceProvider

sealed class ErrorData : StateData {

    data class NetworkErrorState(
        val resources: ResourceProvider,
        override val icon: Int? = R.drawable.img_network_error,
        override val title: String? = resources.getString(R.string.error_network_title),
        override val description: String? = resources.getString(R.string.error_network_description),
        override val button: String? = null
    ) : ErrorData()

    data class ErrorState(
        val resources: ResourceProvider,
        override val icon: Int? = R.drawable.img_network_error,
        override val title: String? = resources.getString(R.string.error_title),
        override val description: String? = resources.getString(R.string.error_description),
        override val button: String? = null
    ) : ErrorData()
}