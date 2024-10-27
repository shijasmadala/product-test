package com.news.product.domain.model

import java.net.IDN

data class ProductModel(
    val id: Int?,
    val thumbnail: String?,
    val title: String?,
    val description: String?,
    val category: String?,
    val price: String?,
    val brand: String?,
    val rating: String?,
    val createdDate: String?,
    val width: String?,
    val height: String?,
    val depth: String?
)
