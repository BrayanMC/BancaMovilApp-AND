package com.bmc.domain.home.usecase

import com.bmc.domain.home.repository.ProductRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun updateProducts() = productRepository.updateProducts()
}