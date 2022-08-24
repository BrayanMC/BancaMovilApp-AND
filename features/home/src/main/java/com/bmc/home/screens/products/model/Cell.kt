package com.bmc.home.screens.products.model

sealed class Cell {
    data class Product(
        val accountName: String = "",
        val account: String = "",
        val currency: String = "",
        val amount: String = ""
    ) : Cell() {
        val salary: String = "$currency $amount"
    }
}