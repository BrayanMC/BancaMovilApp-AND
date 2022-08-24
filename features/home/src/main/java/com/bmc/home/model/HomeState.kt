package com.bmc.home.model

import com.bmc.common.liveData.SingleEvent
import com.bmc.core.base.BaseViewState

data class HomeState(
    val isLoading: Boolean = false,
    val products: SingleEvent<ArrayList<Any>?> = SingleEvent(null)
) : BaseViewState