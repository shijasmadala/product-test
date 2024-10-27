package com.news.product.data.source

import com.news.product.data.dto.Product
import com.news.product.data.dto.ProductDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("products?limit=50&skip=0")
    suspend fun getProduct(): ApiResponse<ProductDto>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") id: String): ApiResponse<Product>
}