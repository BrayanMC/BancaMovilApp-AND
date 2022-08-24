package com.bmc.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.bmc.common.liveData.SingleEvent
import com.bmc.core.base.BaseViewModel
import com.bmc.domain.home.model.Product
import com.bmc.domain.home.usecase.GetProduceUseCase
import com.bmc.domain.home.usecase.UpdateProductUseCase
import com.bmc.domain.util.Result
import com.bmc.home.model.HomeState
import com.bmc.home.screens.products.model.mapper.toArrayListOfAny
import com.bmc.home.screens.products.model.mapper.toItemProduct
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getProduceUseCase: GetProduceUseCase,
    private val updateProductUseCase: UpdateProductUseCase
) : BaseViewModel<HomeState>() {

    override val initialState = HomeState()

    init {
        state = initialState
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            val result = getProduceUseCase.getProducts()
            delay(500)
            handleOnResult(result)
        }
    }

    fun updateProducts() {
        viewModelScope.launch {
            val result = updateProductUseCase.updateProducts()
            delay(500)
            handleOnResult(result)
        }
    }

    private fun handleOnResult(result: Result<List<Product>>) {
        when (result) {
            is Result.Success -> {
                state = state.copy(
                    products = SingleEvent(result.data.toItemProduct().toArrayListOfAny())
                )
            }
            is Result.Error -> {}
        }
    }
}