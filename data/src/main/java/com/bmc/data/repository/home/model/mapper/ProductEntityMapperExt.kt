package com.bmc.data.repository.home.model.mapper

import com.bmc.data.repository.home.model.ProductEntity
import com.bmc.domain.home.model.Product

fun List<ProductEntity>.toDomain(): List<Product> {
    return map {
        Product(
            it.accountName, it.account, it.currency, it.amount
        )
    }
}