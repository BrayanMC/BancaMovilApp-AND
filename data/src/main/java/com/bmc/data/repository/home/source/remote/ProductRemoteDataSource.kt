package com.bmc.data.repository.home.source.remote

import com.bmc.data.repository.home.model.ProductEntity
import com.bmc.data.repository.home.source.ProductDataSource
import com.bmc.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRemoteDataSource : ProductDataSource {

    override suspend fun getProducts(): Result<List<ProductEntity>> {
        return withContext(Dispatchers.IO) {
            Result.Success(mockGetProducts())
        }
    }

    override suspend fun updateProducts(): Result<List<ProductEntity>> {
        return withContext(Dispatchers.IO) {
            Result.Success(mockUpdateProducts())
        }
    }

    private fun mockGetProducts() = listOf(
        ProductEntity(
            accountName = "Cuenta soles",
            account = "1234567890",
            currency = "S/",
            amount = "1,000.80"
        ),
        ProductEntity(
            accountName = "Cuenta dólares",
            account = "0987654321",
            currency = "US$",
            amount = "1,600.20"
        )
    )

    private fun mockUpdateProducts() = listOf(
        ProductEntity(
            accountName = "Cuenta soles",
            account = "1234567890",
            currency = "S/",
            amount = "1,000.80"
        ),
        ProductEntity(
            accountName = "Cuenta dólares",
            account = "0987654321",
            currency = "US$",
            amount = "1,600.20"
        ),
        ProductEntity(
            accountName = "Cuenta soles",
            account = "5432167890",
            currency = "S/",
            amount = "0.00"
        ),
    )
}