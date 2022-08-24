package com.bmc.domain.home.repository

import com.bmc.domain.home.model.Product
import com.bmc.domain.util.Result

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun updateProducts(): Result<List<Product>>
}