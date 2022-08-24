package com.bmc.data.repository.home

import com.bmc.data.repository.home.model.mapper.toDomain
import com.bmc.data.repository.home.source.ProductDataSource
import com.bmc.domain.home.repository.ProductRepository
import com.bmc.domain.home.model.Product
import com.bmc.domain.util.Result
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource
) : ProductRepository {

    override suspend fun getProducts(): Result<List<Product>> {
        return when (val result = productDataSource.getProducts()) {
            is Result.Success -> Result.Success(result.data.toDomain())
            is Result.Error -> result
        }
    }

    override suspend fun updateProducts(): Result<List<Product>> {
        return when (val result = productDataSource.updateProducts()) {
            is Result.Success -> Result.Success(result.data.toDomain())
            is Result.Error -> result
        }
    }
}