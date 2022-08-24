package com.bmc.domain.home.usecase

import com.bmc.domain.home.repository.ProductRepository
import javax.inject.Inject

class GetProduceUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun getProducts() = productRepository.getProducts()
}