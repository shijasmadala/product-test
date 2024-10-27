package com.news.product.utils

import com.news.product.data.dto.Product
import com.news.product.data.dto.ProductDto
import com.news.product.domain.model.ProductModel

fun Product.toProductModel() : ProductModel {
    return ProductModel(
        title = title,
        description = description,
        category = category,
        price = price.toString(),
        thumbnail = thumbnail,
        brand = brand,
        rating = rating.toString(),
        id = id,
        createdDate = meta?.createdAt?:"",
        width = dimensions?.width.toString(),
        height = dimensions?.height.toString(),
        depth = dimensions?.depth.toString()
    )
}