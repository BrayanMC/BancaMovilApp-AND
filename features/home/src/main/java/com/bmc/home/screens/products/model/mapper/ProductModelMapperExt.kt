package com.bmc.home.screens.products.model.mapper

import com.bmc.domain.home.model.Product
import com.bmc.home.screens.products.model.Cell
import com.bmc.home.screens.products.model.ProductModel

fun List<Product>.toModel(): List<ProductModel> {
    return map {
        ProductModel(
            it.accountName, it.account, it.currency, it.amount
        )
    }
}

fun List<Product>.toItemProduct(): List<Cell.Product> {
    return map {
        Cell.Product(
            it.accountName, it.account, it.currency, it.amount
        )
    }
}

fun List<Cell.Product>.toArrayListOfAny(): ArrayList<Any> {
    val listCells = ArrayList<Any>()
    forEach { cell ->
        listCells.add(cell)
    }
    return listCells
}