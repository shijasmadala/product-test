package com.news.product.domain.repository

import com.news.product.data.dto.ProductDto
import com.news.product.domain.model.ProductModel
import com.news.product.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getProduct() : Flow<Resource<List<ProductModel>>>

    suspend fun getProductDetails(id: String) : Flow<Resource<ProductModel>>
}