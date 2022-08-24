package com.bmc.data.repository.home.source

import com.bmc.data.repository.home.model.ProductEntity
import com.bmc.domain.util.Result

interface ProductDataSource {
    suspend fun getProducts(): Result<List<ProductEntity>>
    suspend fun updateProducts(): Result<List<ProductEntity>>
}