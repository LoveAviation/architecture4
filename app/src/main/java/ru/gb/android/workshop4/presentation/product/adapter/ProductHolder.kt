package ru.gb.android.workshop4.presentation.product.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.gb.android.workshop4.marketsample.R
import ru.gb.android.workshop4.marketsample.databinding.ItemProductBinding
import ru.gb.android.workshop4.presentation.product.ProductState

class ProductHolder(
    private val binding: ItemProductBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productState: ProductState) {
        binding.image.load(productState.image)
        binding.name.text = productState.name
        binding.price.text =
            binding.root.resources.getString(R.string.price_with_arg, productState.price)
    }
}
