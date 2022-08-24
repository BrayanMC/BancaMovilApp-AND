package com.bmc.home.screens.products.model

import com.bmc.common.liveData.SingleEvent
import com.bmc.core.base.BaseViewState

data class ProductsState(
    val isLoading: Boolean = false,
    val products: SingleEvent<List<Any>?> = SingleEvent(null)
) : BaseViewState