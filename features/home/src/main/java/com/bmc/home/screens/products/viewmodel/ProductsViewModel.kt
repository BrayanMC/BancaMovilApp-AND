package com.bmc.home.screens.products.viewmodel

import com.bmc.common.liveData.SingleEvent
import com.bmc.core.base.BaseViewModel
import com.bmc.home.screens.products.model.ProductsState

class ProductsViewModel : BaseViewModel<ProductsState>() {

    override val initialState = ProductsState()

    init {
        state = initialState
    }

    fun setProducts(products: ArrayList<Any>?) {
        state = state.copy(products = SingleEvent(products))
    }
}