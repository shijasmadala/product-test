package com.news.product.presentation.home

import com.news.product.domain.model.ProductModel

sealed class HomeUiState {
    data class Success(val productList: List<ProductModel>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
    object Loading : HomeUiState()
    object Empty : HomeUiState()
}