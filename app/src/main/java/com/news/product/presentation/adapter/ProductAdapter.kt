package com.news.product.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.news.product.R
import com.news.product.databinding.ItemProductBinding
import com.news.product.domain.model.ProductModel

class ProductAdapter(private val listener: OnItemClick) :
    ListAdapter<ProductModel, RecyclerView.ViewHolder>(ProductDiffCallback) {
    private var originalList: List<ProductModel> = emptyList()

    interface OnItemClick {
        fun onItemClick(productModel: ProductModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).bind(getItem(position))
    }

    private inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productModel: ProductModel) {
            binding.apply {
                img.load(productModel.thumbnail) {
                    crossfade(true)
                    placeholder(R.drawable.error) // Placeholder image
                    error(R.drawable.error)
                }
                title.text = productModel.title
                description.text = productModel.description
                brand.text = "Brand: ${productModel.brand}"
                price.text = "Price: ${productModel.price}"
                root.setOnClickListener {
                    listener.onItemClick(productModel)
                }
            }
        }
    }

    fun setOriginalList(list: List<ProductModel>) {
        originalList = list
        submitList(list)
    }

    fun filterItems(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter {
                it.title?.lowercase()!!.contains(query.lowercase())
            }
        }
        submitList(filteredList)
    }
}


object ProductDiffCallback : DiffUtil.ItemCallback<ProductModel>() {
    override fun areItemsTheSame(
        oldItem: ProductModel,
        newItem: ProductModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ProductModel,
        newItem: ProductModel
    ): Boolean {
        return oldItem == newItem
    }
}