package com.bmc.bancamovilapp.di.home

import com.bmc.data.repository.home.ProductRepositoryImpl
import com.bmc.data.repository.home.source.ProductDataSource
import com.bmc.data.repository.home.source.remote.ProductRemoteDataSource
import com.bmc.domain.home.repository.ProductRepository
import dagger.Module
import dagger.Provides

@Module
class ProductRepositoryModule {

    @Provides
    fun provideProductRepository(
        productDataSource: ProductDataSource
    ): ProductRepository {
        return ProductRepositoryImpl(productDataSource)
    }

    @Provides
    fun provideProductDataSource(): ProductDataSource {
        return ProductRemoteDataSource()
    }
}