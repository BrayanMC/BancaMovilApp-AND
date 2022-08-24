package com.bmc.home.screens.products.adapter.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bmc.home.databinding.ItemHomeProductBinding
import com.bmc.home.screens.products.model.Cell

class ProductProductViewHolder(
    private val binding: ItemHomeProductBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup): ProductProductViewHolder {
            val binding =
                ItemHomeProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ProductProductViewHolder(binding)
        }
    }

    fun onBind(cell: Cell.Product, position: Int, onClick: (Int) -> Unit) {
        binding.apply {
            productItem = cell
            root.setOnClickListener { onClick(position) }
        }
    }
}