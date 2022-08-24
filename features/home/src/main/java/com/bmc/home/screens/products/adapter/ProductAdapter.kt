package com.bmc.home.screens.products.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bmc.common.utils.AutoUpdatableAdapter
import com.bmc.home.R
import com.bmc.home.screens.products.adapter.viewholders.ProductProductViewHolder
import com.bmc.home.screens.products.model.Cell
import kotlin.properties.Delegates

class ProductAdapter(var onClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdatableAdapter {

    var items: List<Any> by Delegates.observable(arrayListOf()) { _, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Cell.Product -> R.layout.item_home_product
            else -> throw IllegalStateException("Unknown view type at position $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_home_product -> ProductProductViewHolder.newInstance(parent)
            else -> throw IllegalStateException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductProductViewHolder -> holder.onBind(
                items[position] as Cell.Product,
                position,
                onClickListener
            )
        }
    }
}