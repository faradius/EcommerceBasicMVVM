package com.alex.ecommercebasicmvvm.presentation.product_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alex.ecommercebasicmvvm.data.Product
import com.alex.ecommercebasicmvvm.databinding.ProductItemBinding

class ProductListAdapter(
    private val onClick: (Product) -> Unit
) : ListAdapter<Product, ProductListAdapter.ProductViewHolder>(
    ProductDiffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    inner class ProductViewHolder(
        itemBinding: ProductItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val image = itemBinding.ivProduct
        private val name = itemBinding.tvProductName
        private val price = itemBinding.tvProductPrice

        private var currentProduct: Product? = null

        init {
            itemView.setOnClickListener {
                currentProduct?.let {
                    onClick(it)
                }
            }
        }

        fun bind(product: Product) {
            currentProduct = product

            name.text = product.name
            price.text = "$${product.price} MXN"

            image.load(product.imageURL)
        }

    }
}

object ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}