package com.bmc.home.extensions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bmc.home.screens.products.adapter.ProductAdapter

@BindingAdapter("products")
fun setProducts(recyclerView: RecyclerView, items: List<Any>?) {
    items?.let {
        (recyclerView.adapter as ProductAdapter).items = it
    }
}